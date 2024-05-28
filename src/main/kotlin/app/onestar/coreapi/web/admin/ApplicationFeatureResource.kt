package app.onestar.coreapi.web.admin

import app.onestar.coreapi.domain.ApplicationFeature
import app.onestar.coreapi.service.ApplicationFeatureService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
        return applicationFeatureService.create(applicationFeature)
    }

    @GetMapping("/application-features")
    fun getAllApplicationFeatures(): Flux<ApplicationFeature> {
        return applicationFeatureService.findAll()
    }

    @GetMapping("/application-features/{id}")
    fun getAllApplicationFeatures(
        @PathVariable id: String,
    ): Mono<ApplicationFeature?> {
        return applicationFeatureService.findById(id)
    }

    @PutMapping("/application-features/{id}")
    fun updateApplicationFeature(
        @PathVariable id: String,
        @RequestBody applicationFeature: ApplicationFeature,
    ): Mono<ApplicationFeature> {
        require(applicationFeature.id == id) { "ApplicationFeature ID must match path ID" }
        return applicationFeatureService.update(applicationFeature)
    }

    @PatchMapping("/application-features/{id}")
    fun patchApplicationFeature(
        @PathVariable id: String,
        @RequestBody applicationFeature: ApplicationFeature,
    ): Mono<ApplicationFeature> {
        require(applicationFeature.id == id) { "ApplicationFeature ID must match path ID" }
        return applicationFeatureService.patch(applicationFeature)
    }

    @DeleteMapping("/application-features/{id}")
    fun deleteApplicationFeature(
        @PathVariable id: String,
    ): Mono<Void> {
        return applicationFeatureService.deleteById(id)
    }
}
