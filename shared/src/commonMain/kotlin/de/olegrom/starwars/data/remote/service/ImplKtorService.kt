package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.FilmDTO
import de.olegrom.starwars.data.remote.dto.FilmsDTO
import de.olegrom.starwars.data.remote.dto.StarshipDTO
import de.olegrom.starwars.data.remote.dto.StarshipsDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ImplKtorService(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : AbstractKtorService() {
    override suspend fun getFilms(): FilmsDTO = httpClient.get("$baseUrl/${EndPoints.FILMS}") {}.body()
    override suspend fun getStarships(): StarshipsDTO = httpClient.get("$baseUrl/${EndPoints.STARSHIPS}") {}.body()
    override suspend fun getFilm(id: String): FilmDTO = httpClient.get("$baseUrl/${EndPoints.FILMS}/${id}") {}.body()
    override suspend fun getStarship(id: String): StarshipDTO = httpClient.get("$baseUrl/${EndPoints.STARSHIPS}/${id}") {}.body()
}

