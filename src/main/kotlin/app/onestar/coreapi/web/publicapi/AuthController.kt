package app.onestar.coreapi.web.publicapi

import app.onestar.coreapi.security.JwtTokenProvider
import app.onestar.coreapi.web.publicapi.api.AuthenticationRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManager: ReactiveAuthenticationManager,
) {
    @PostMapping("/authenticate")
    fun authenticate(
        @RequestBody request: Mono<AuthenticationRequest>,
    ): Mono<Any> {
        return request.flatMap {
            this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken(it.username, it.password))
                .map { auth -> jwtTokenProvider.createToken(auth) }
        }
            .map {
                val httpHeaders = HttpHeaders()
                httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer $it")
                val tokenBody = mapOf("token" to it)
                ResponseEntity(tokenBody, httpHeaders, HttpStatus.OK)
            }
    }

    @GetMapping("/unauthorized")
    fun unauthorized(): Mono<String> {
        return Mono.just("Hello World")
    }
}
