package dev.isxander.web.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.*

val modsDB = mongoClient.getDatabase("mods")
val modsCol = modsDB.getCollection<Mod>("mods")

@Serializable
data class Mod(
    val id: String,
    val name: String,
    val summary: String,
    @SerialName("icon_url") val iconUrl: String,
    @SerialName("modrinth_url") val modrinthUrl: String,
    @SerialName("curseforge_url") val curseforgeUrl: String,
    @SerialName("last_updated") val lastUpdated: Long
)

fun getPaginatedMods(page: Int, limit: Int): List<Mod> {
    return modsCol.find(
        limit(limit),
        skip(page - 1 * limit),
        sort(descending(Mod::lastUpdated))
    ).partial(true).toList()
}

fun getMod(id: String): Mod? {
    return modsCol.findOne(Mod::id eq id)
}