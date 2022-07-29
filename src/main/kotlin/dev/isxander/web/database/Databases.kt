package dev.isxander.web.database

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import dev.isxander.web.utils.MONGO_ADDRESS
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

val mongoClient = KMongo.createClient(MONGO_ADDRESS)

inline fun <reified T : Any> MongoDatabase.getButDontCreateCollection(name: String): MongoCollection<T>? {
    if (this.listCollectionNames().any { it == name }) {
        return getCollection<T>(name)
    }

    return null
}