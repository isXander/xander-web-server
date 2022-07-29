package dev.isxander.web.responses

import kotlinx.serialization.Serializable

@Serializable
data class ErrorData(val reason: String)
