package app.onestar.coreapi.domain

import app.onestar.coreapi.domain.common.AbstractAuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity
@Table(name = "application_feature")
data class ApplicationFeature(
    @Id
    @Column(name = "id")
    override var id: String? = generateId(ApplicationFeature::class),
    @Column(name = "key")
    @get:NotNull
    var key: String? = null,
    @Column(name = "value")
    @get:NotNull
    var value: String? = null,
) : Serializable, AbstractAuditingEntity() {
    override fun toString(): String {
        return """
            ApplicationFeature{
                id=$id,
                key=$key,
                value=$value,
                createdBy=$createdBy,
                createdDate=$createdDate,
                lastModifiedBy=$lastModifiedBy,
                lastModifiedDate=$lastModifiedDate
            }
            """.trimIndent()
    }
}
