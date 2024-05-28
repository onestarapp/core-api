package app.onestar.coreapi.domain.common

import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.stereotype.Component

@Component
class AuditingEntityListener {
    @PreUpdate
    @PrePersist
    fun onBeforeSave(entity: AbstractAuditingEntity) {
//        val now = Instant.now()
//        if (entity.createdDate == null) {
//            entity.createdDate = now
//            entity.createdBy = getCurrentUser() // Or get current user from security context
//        }
//        entity.lastModifiedDate = now
//        entity.lastModifiedBy = getCurrentUser() // Or get current user from security context
    }
}
