package app.onestar.coreapi.security

import org.springframework.security.core.context.SecurityContextHolder

fun getCurrentUser(): String {
    val authentication = SecurityContextHolder.getContext().authentication
    return if (authentication != null && authentication.name.isNotEmpty()) {
        authentication.name
    } else {
        "system"
    }
}
