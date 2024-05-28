package app.onestar.coreapi.domain

import app.onestar.coreapi.domain.common.AbstractAuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = "application_feature")
// @EntityListeners(AuditingEntityListener::class)
data class ApplicationFeature(
    @Id
    @Column(name = "id")
    override var id: String = generateId(),
    @Column(name = "key")
    @get:NotNull
    var key: String? = null,
    @Column(name = "value")
    @get:NotNull
    var value: String? = null,
) : Serializable, AbstractAuditingEntity() {
    companion object {
        fun generateId(): String {
            return "0af-${UUID.randomUUID()}"
        }
    }
}
