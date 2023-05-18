package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.FilmsDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ImplKtorService(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : AbstractKtorService() {

    private val apikey = "a52b414d7a4e496a81b9787ebf8993f2"
    override suspend fun getFilms(
        pageSize: Int,
        page: Int,
    ): FilmsDTO = httpClient.get("$baseUrl/${EndPoints.FILMS}") {
        header("x-api-key", apikey)
        parameter("pageSize", pageSize)
        parameter("page", page)
    }.body()
    }

