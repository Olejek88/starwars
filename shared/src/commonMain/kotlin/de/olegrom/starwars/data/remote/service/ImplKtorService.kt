package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ImplKtorService(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : AbstractKtorService() {
    override suspend fun getFilms(page: Int): FilmsDTO =
        httpClient.get("$baseUrl/${EndPoints.FILMS}/?page=${page}") {}.body()

    override suspend fun getStarships(page: Int): StarshipsDTO =
        httpClient.get("$baseUrl/${EndPoints.STARSHIPS}/?page=${page}") {}.body()

    override suspend fun getFilm(id: String): FilmDTO =
        httpClient.get("$baseUrl/${EndPoints.FILMS}/${id}") {}.body()

    override suspend fun getStarship(id: String): StarshipDTO =
        httpClient.get("$baseUrl/${EndPoints.STARSHIPS}/${id}") {}.body()

    override suspend fun getPersons(page: Int): PersonsDTO =
        httpClient.get("$baseUrl/${EndPoints.PEOPLES}/?page=${page}") {}.body()

    override suspend fun getPlanets(page: Int): PlanetsDTO =
        httpClient.get("$baseUrl/${EndPoints.PLANETS}/?page=${page}") {}.body()

    override suspend fun getPerson(id: String): PersonDTO =
        httpClient.get("$baseUrl/${EndPoints.PEOPLES}/${id}") {}.body()

    override suspend fun getPlanet(id: String): PlanetDTO =
        httpClient.get("$baseUrl/${EndPoints.PLANETS}/${id}") {}.body()
}
