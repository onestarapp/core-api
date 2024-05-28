package app.onestar.coreapi.web.publicapi.api

data class AuthenticationRequest(
    val username: String,
    val password: String,
)
