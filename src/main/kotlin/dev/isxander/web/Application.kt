package dev.isxander.web

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import dev.isxander.web.plugins.*
import dev.isxander.web.utils.HOST
import dev.isxander.web.utils.PORT

fun main() {
    embeddedServer(Netty, port = PORT, host = HOST) {
        configureSerialization()
        configureRouting()
        configureStatusPages()
        configureRateLimit()
    }.start(wait = true)
}
