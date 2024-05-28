package app.onestar.coreapi.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "application_feature")
data class ApplicationFeature(
    @Id
    val id: String = generateId(),
    val key: String = "",
    val value: String = ""
) {
    companion object {
        fun generateId(): String {
            return "0af-${UUID.randomUUID()}"
        }
    }
}