package dev.isxander.web.plugins

import dev.isxander.web.database.getMod
import dev.isxander.web.database.getPaginatedMods
import dev.isxander.web.responses.DataResponse
import dev.isxander.web.responses.PaginatedModListResponse
import dev.isxander.web.responses.SingleModResponse
import dev.isxander.web.utils.STATIC_FOLDER
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import java.io.File

fun Application.configureRouting() {
    routing {
        static("/") {
            staticRootFolder = File(STATIC_FOLDER)
            files(".")
            default("index.html")
        }

        get("/api/v1/mods") {
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1
            val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 20
            if (limit > 100) throw BadRequestException("page limit cannot be over 100")

            val mods = getPaginatedMods(page, limit)

            call.respond(HttpStatusCode.OK, DataResponse(success = true, PaginatedModListResponse(page, limit, mods)))
        }

        get("/api/v1/mod/{id}") {
            val modId = call.parameters["id"] ?: throw BadRequestException("mod id required")
            val mod = getMod(modId) ?: throw BadRequestException("unknown mod $modId")

            call.respond(HttpStatusCode.OK, DataResponse(success = true, SingleModResponse(mod)))
        }
    }
}

