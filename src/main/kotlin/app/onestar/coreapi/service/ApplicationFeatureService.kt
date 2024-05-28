package app.onestar.coreapi.service

import app.onestar.coreapi.domain.ApplicationFeature
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ApplicationFeatureService {
    fun save(applicationFeature: ApplicationFeature): Mono<ApplicationFeature>

    fun findById(id: String): ApplicationFeature?

    fun findAll(): Flux<ApplicationFeature>
}