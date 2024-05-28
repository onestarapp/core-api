package app.onestar.coreapi.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    private val environment: Environment,
) {
    companion object {
        private const val AUTHORITIES_KEY = "roles"
        private val log = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    }

    private lateinit var secretKey: SecretKey

    @PostConstruct
    fun init() {
        val jwtSecret = environment.getProperty("application.jwt.secret") ?: throw IllegalArgumentException("JWT secret must not be null")
        val secret = Base64.getEncoder().encodeToString(jwtSecret.toByteArray())
        secretKey = Keys.hmacShaKeyFor(secret?.toByteArray(StandardCharsets.UTF_8))
    }

    fun createToken(authentication: Authentication): String {
        val username: String = authentication.name
        val authorities: Collection<GrantedAuthority> = authentication.authorities
        val claims: Claims = Jwts.claims().setSubject(username)
        if (authorities.isNotEmpty()) {
            claims[AUTHORITIES_KEY] = authorities.joinToString(",") { it.authority }
        }
        val now = Date()
        val validityInMs =
            environment.getProperty("application.jwt.token-validity-in-ms")?.toLong()
                ?: throw IllegalArgumentException("JWT validity must not be null")
        val validity = Date(now.time + validityInMs)
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
            .signWith(secretKey, SignatureAlgorithm.HS256).compact()
    }

    fun getAuthentication(token: String?): Authentication {
        val claims: Claims =
            Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody()
        val authoritiesClaim = claims[AUTHORITIES_KEY]
        val authorities: Collection<GrantedAuthority> =
            if (authoritiesClaim == null) {
                AuthorityUtils.NO_AUTHORITIES
            } else {
                AuthorityUtils
                    .commaSeparatedStringToAuthorityList(authoritiesClaim.toString())
            }
        val principal = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(token: String?): Boolean {
        try {
            val claims: Jws<Claims> =
                Jwts.parserBuilder().setSigningKey(secretKey)
                    .build().parseClaimsJws(token)
            // parseClaimsJws will check expiration date. No need do here.
//            log.info("expiration date: {}", claims.body.expiration)
            return true
        } catch (e: JwtException) {
//            log.info("Invalid JWT token: {}", e.message)
//            log.trace("Invalid JWT token trace.", e)
        } catch (e: IllegalArgumentException) {
//            log.info("Invalid JWT token: {}", e.message)
//            log.trace("Invalid JWT token trace.", e)
        }
        return false
    }
}
