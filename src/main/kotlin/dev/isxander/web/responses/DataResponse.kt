package dev.isxander.web.responses

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(val success: Boolean = true, val data: T)
