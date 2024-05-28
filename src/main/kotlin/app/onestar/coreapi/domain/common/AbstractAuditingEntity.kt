package app.onestar.coreapi.domain.common

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.Instant
import java.util.UUID
import kotlin.reflect.KClass

@MappedSuperclass
@JsonIgnoreProperties(value = [ "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate", "version" ], allowGetters = true)
abstract class AbstractAuditingEntity(
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
    @Version
    @Column(name = "version")
    open var version: Long? = 0,
) : Serializable {
    abstract val id: String?

    companion object {
        fun <T : AbstractAuditingEntity> generateId(clazz: KClass<T>): String {
            return "${clazz.simpleName}-${UUID.randomUUID()}"
        }
    }
}
