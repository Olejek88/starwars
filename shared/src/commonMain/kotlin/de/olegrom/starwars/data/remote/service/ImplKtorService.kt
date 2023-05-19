package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.FilmsDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ImplKtorService(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : AbstractKtorService() {
    override suspend fun getFilms(): FilmsDTO = httpClient.get("$baseUrl/${EndPoints.FILMS}") {
    }.body()
}

