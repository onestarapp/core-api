package app.onestar.coreapi.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration : WebFluxConfigurer {
    @Value("\${application.jwt.secret}")
    private val jwtSecret: String? = null

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user: UserDetails =
            User
                .withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build()
        return MapReactiveUserDetailsService(user)
    }

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange {
                it
                    .pathMatchers("/api/auth/**").permitAll()
                    .pathMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                    .pathMatchers("/api/**").authenticated()
            }.csrf { it.disable() }.build()
    }
}
