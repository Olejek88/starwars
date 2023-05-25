package de.olegrom.starwars.util

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json

@Suppress("OPT_IN_IS_NOT_ENABLED")
class FakeApiService {
    fun buildApiService(respondFileName: String): HttpClient {
        val response = FileReader().readFile(respondFileName)
        val mockEngine = MockEngine {
            if (respondFileName != "") {
                respond(
                    content = ByteReadChannel(response),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else respond(content = "", status = HttpStatusCode.BadGateway)
        }
        return HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}