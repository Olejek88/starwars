package de.olegrom.starwars.data.repository

import de.olegrom.starwars.data.remote.service.AbstractKtorService

class ImplRepository(
    private val ktorService: AbstractKtorService
) : AbstractRepository() {
    override suspend fun getAllFilms(page: Int) = ktorService.getFilms(page)
    override suspend fun getAllStarships(page: Int) = ktorService.getStarships(page)
    override suspend fun getFilm(id: String) = ktorService.getFilm(id)
    override suspend fun getStarship(id: String) = ktorService.getStarship(id)
    override suspend fun getAllPlanets(page: Int) = ktorService.getPlanets(page)
    override suspend fun getAllPersons(page: Int) = ktorService.getPersons(page)
    override suspend fun getPlanet(id: String) = ktorService.getPlanet(id)
    override suspend fun getPerson(id: String) = ktorService.getPerson(id)
}




