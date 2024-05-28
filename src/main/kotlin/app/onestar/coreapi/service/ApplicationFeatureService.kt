package app.onestar.coreapi.service

import app.onestar.coreapi.domain.ApplicationFeature
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ApplicationFeatureService {
    fun save(applicationFeature: ApplicationFeature): Mono<ApplicationFeature>

    fun findById(id: String): Mono<ApplicationFeature?>

    fun findAll(): Flux<ApplicationFeature>

    fun deleteById(id: String): Mono<Void>
}
