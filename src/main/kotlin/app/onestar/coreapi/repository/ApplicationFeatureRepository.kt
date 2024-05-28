package app.onestar.coreapi.repository

import app.onestar.coreapi.domain.ApplicationFeature
import org.reactivestreams.Publisher
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ApplicationFeatureRepository : R2dbcRepository<ApplicationFeature, String> {
    @Query("SELECT * FROM application_feature WHERE id = :id")
    override fun findById(id: String): Mono<ApplicationFeature?>

    @Query("DELETE FROM application_feature WHERE id = :id")
    override fun deleteById(id: Publisher<String>): Mono<Void>
}
