package app.onestar.coreapi.controller.publicapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController("/api/auth")
class AuthenticationController {
    @GetMapping("/authenticate")
    fun authenticate(): Mono<String> {
        return Mono.just("Authenticated")
    }
}
