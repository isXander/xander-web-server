package dev.isxander.web.utils

val HOST = env("HOST") ?: "0.0.0.0"
val PORT = env("PORT")?.toIntOrNull() ?: 8080
val MONGO_ADDRESS = env("MONGO_ADDRESS") ?: "mongodb://localhost"
//val JWT_SECRET = env("JWT_SECRET") ?: error("JWT_SECRET not set")
val STATIC_FOLDER = env("STATIC_FOLDER") ?: "static"