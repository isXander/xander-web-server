package dev.isxander.web.plugins

import dev.isxander.web.responses.DataResponse
import dev.isxander.web.responses.ErrorData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, status ->
            call.respond(status, DataResponse(false, ErrorData("Endpoint not found.")))
        }
        status(HttpStatusCode.Forbidden) { call, status ->
            call.respond(status, DataResponse(false, ErrorData("Endpoint forbidden!")))
        }
        status(HttpStatusCode.TooManyRequests) { call, status ->
            call.respond(status, DataResponse(false, ErrorData("Too many requests! (30 every minute)")))
        }
        exception<BadRequestException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, DataResponse(false, ErrorData("Bad request: ${cause.message ?: ""}")))
        }
    }
}