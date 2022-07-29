package dev.isxander.web.plugins

import dev.forst.ktor.ratelimiting.RateLimiting
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import java.time.Duration

fun Application.configureRateLimit() {
    install(RateLimiting) {
        registerLimit(
            limit = 30,
            window = Duration.ofMinutes(1)
        ) {
            request.origin.host
        }

        excludeRequestWhen { !request.path().startsWith("/api") }
    }
}