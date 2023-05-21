package de.olegrom.starwars.data.repository

import de.olegrom.starwars.data.remote.service.AbstractKtorService

class ImplRepository(
    private val ktorService: AbstractKtorService
) : AbstractRepository() {
    override suspend fun getAllFilms() = ktorService.getFilms()
    override suspend fun getAllStarships() = ktorService.getStarships()
    override suspend fun getFilm(id: String) = ktorService.getFilm(id)
    override suspend fun getStarship(id: String) = ktorService.getStarship(id)
    override suspend fun getAllPlanets() = ktorService.getPlanets()
    override suspend fun getAllPersons() = ktorService.getPersons()
    override suspend fun getPlanet(id: String) = ktorService.getPlanet(id)
    override suspend fun getPerson(id: String) = ktorService.getPerson(id)
}




