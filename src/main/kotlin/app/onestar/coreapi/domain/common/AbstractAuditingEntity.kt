package app.onestar.coreapi.domain.common

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.Instant
import java.util.UUID

@MappedSuperclass
@JsonIgnoreProperties(value = [ "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" ], allowGetters = true)
abstract class AbstractAuditingEntity(
    @Id
    @Column(name = "id")
    open var id: String? = null,
    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 64, updatable = false)
    open var createdBy: String? = null,
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    open var createdDate: Instant? = Instant.now(),
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 64)
    open var lastModifiedBy: String? = null,
    @LastModifiedDate
    @Column(name = "last_modified_date")
    open var lastModifiedDate: Instant? = Instant.now(),
) : Serializable {
    companion object {
        fun generateId(): String {
            return "0af-${UUID.randomUUID()}"
        }
    }
}
