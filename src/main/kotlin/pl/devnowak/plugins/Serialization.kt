package pl.devnowak.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class ShelterEntity(val id: Int, val name: String, val description: String)

val data: List<ShelterEntity> = List(15) {
    ShelterEntity(
        id = it,
        name = "Name $it",
        description = "Description $it"
    )
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/shelters/{count}") {
            val count = call.parameters["count"]!!.toInt()
            call.respond(data.take(count))
        }
    }
}
