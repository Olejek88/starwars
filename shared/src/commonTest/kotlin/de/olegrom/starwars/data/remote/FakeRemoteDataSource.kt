package de.olegrom.starwars.data.remote

import de.olegrom.starwars.data.remote.dto.FilmDTO
import de.olegrom.starwars.data.remote.dto.FilmsDTO
import de.olegrom.starwars.util.FakeApiService
import io.ktor.client.call.*
import io.ktor.client.request.*

class FakeRemoteDataSource {
    val apiService = FakeApiService()
    private var movieListResponse = ""
    private var movieDetailResponse = ""

    fun setMovieListResponse(fileName: String) {
        movieListResponse = fileName
    }

    fun setMovieDetailResponse(fileName: String) {
        movieDetailResponse = fileName
    }

    suspend fun loadMovies(): FilmsDTO {
        val api = apiService.buildApiService(movieListResponse)
        return api.get(urlString = "https://swapi.py4e.com/api/films") {}.body()
    }

    suspend fun fetchMovieDetail(movieId: String): FilmDTO {
        val api = apiService.buildApiService(movieDetailResponse)
        return api.get(urlString = "https://swapi.py4e.com/api/films/${movieId}") {}.body()
    }
}