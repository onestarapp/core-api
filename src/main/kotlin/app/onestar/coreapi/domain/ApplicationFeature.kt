package app.onestar.coreapi.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jetbrains.annotations.NotNull
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "application_feature")
data class ApplicationFeature(
    @Id
    @Column(name = "id")
    var id: String = generateId(),
    @Column(name = "key")
    @get:NotNull
    var key: String? = null,
    @Column(name = "value")
    @get:NotNull
    var value: String? = null,
    @Column(name = "created_by")
    @get:NotNull
    var createdBy: String? = null,
    @Column(name = "created_date")
    @get:NotNull
    var createdDate: Instant? = Instant.now(),
    @Column(name = "last_modified_by")
    @get:NotNull
    var lastModifiedBy: String? = null,
    @Column(name = "last_modified_date")
    @get:NotNull
    var lastModifiedDate: Instant? = Instant.now(),
) {
    companion object {
        fun generateId(): String {
            return "0af-${UUID.randomUUID()}"
        }
    }
}
