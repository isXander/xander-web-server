package dev.isxander.web.responses

import dev.isxander.web.database.Mod
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedModListResponse(val page: Int, val limit: Int, val mods: List<Mod>)
