package app.onestar.coreapi.repository

import app.onestar.coreapi.domain.ApplicationFeature
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationFeatureRepository : ReactiveCrudRepository<ApplicationFeature, String>
