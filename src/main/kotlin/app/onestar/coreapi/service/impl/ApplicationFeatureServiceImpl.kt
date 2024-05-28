package app.onestar.coreapi.service.impl

import app.onestar.coreapi.domain.ApplicationFeature
import app.onestar.coreapi.repository.ApplicationFeatureRepository
import app.onestar.coreapi.service.ApplicationFeatureService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ApplicationFeatureServiceImpl(
    private val applicationFeatureRepository: ApplicationFeatureRepository,
) : ApplicationFeatureService {
    override fun save(applicationFeature: ApplicationFeature): Mono<ApplicationFeature> {
        return applicationFeatureRepository.save(applicationFeature)
    }

    override fun findById(id: String): Mono<ApplicationFeature?> {
        return applicationFeatureRepository.findById(id)
    }

    override fun findAll(): Flux<ApplicationFeature> {
        return applicationFeatureRepository.findAll()
    }

    override fun deleteById(id: String): Mono<Void> {
        return applicationFeatureRepository.deleteById(id)
    }
}
