package app.onestar.coreapi.web.admin

import app.onestar.coreapi.domain.ApplicationFeature
import app.onestar.coreapi.service.ApplicationFeatureService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/admin")
class ApplicationFeatureResource(
    private val applicationFeatureService: ApplicationFeatureService,
) {
    @PostMapping("/application-features")
    fun saveApplicationFeature(
        @RequestBody applicationFeature: ApplicationFeature,
    ): Mono<ApplicationFeature> {
        return applicationFeatureService.save(applicationFeature)
    }

    @GetMapping("/application-features")
    fun getAllApplicationFeatures(): Flux<ApplicationFeature> {
        return applicationFeatureService.findAll()
    }
}
