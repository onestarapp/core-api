package app.onestar.coreapi.service

import app.onestar.coreapi.domain.ApplicationFeature
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ApplicationFeatureService {
    fun create(applicationFeature: ApplicationFeature): Mono<ApplicationFeature>
    fun update(applicationFeature: ApplicationFeature): Mono<ApplicationFeature>
    fun patch(applicationFeature: ApplicationFeature): Mono<ApplicationFeature>

    fun findById(id: String): Mono<ApplicationFeature?>

    fun findAll(): Flux<ApplicationFeature>

    fun deleteById(id: String): Mono<Void>
}
