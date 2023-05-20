package de.olegrom.starwars.data.remote.service

import de.olegrom.starwars.data.remote.dto.FilmDTO
import de.olegrom.starwars.data.remote.dto.FilmsDTO
import de.olegrom.starwars.data.remote.dto.StarshipDTO
import de.olegrom.starwars.data.remote.dto.StarshipsDTO

abstract class AbstractKtorService {
    abstract suspend fun getFilms(): FilmsDTO
    abstract suspend fun getStarships(): StarshipsDTO
    abstract suspend fun getFilm(id: String): FilmDTO
    abstract suspend fun getStarship(id: String): StarshipDTO
}

