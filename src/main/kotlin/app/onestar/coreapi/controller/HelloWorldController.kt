package app.onestar.coreapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HelloWorldController {
    @GetMapping("/hello")
    fun helloWorld(): Mono<String> {
        return Mono.just("Hello World")
    }
}
