package app.onestar.coreapi.api.publicapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/auth")
class AuthenticationController {
    @PostMapping("/authenticate")
    fun authenticate(): Mono<String> {
        return Mono.just("Authenticated")
    }

    @GetMapping("/unauthorized")
    fun unauthorized(): Mono<String> {
        return Mono.just("Hello World")
    }
}
