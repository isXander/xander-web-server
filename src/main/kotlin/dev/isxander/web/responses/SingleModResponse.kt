package dev.isxander.web.responses

import dev.isxander.web.database.Mod
import kotlinx.serialization.Serializable

@Serializable
data class SingleModResponse(val mod: Mod)
