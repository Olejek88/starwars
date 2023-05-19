package de.olegrom.starwars.data.remote.dto

import de.olegrom.starwars.domain.domain_model.FilmDomainModel

@kotlinx.serialization.Serializable
data class FilmsDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Film>,
)

@kotlinx.serialization.Serializable
data class Film(
    val title: String = "Undefined",
    var episode_id: Int = 2,
    var openingCrawl: String = "",
    var director: String = "",
    var producer: String = "",
    var release_date: String = ""
    //var characters: List<> = listOf(),
    //var planets: List<> = listOf(),
    //var starships: List<> = listOf(),
)

fun FilmsDTO.asDomainModel(): List<FilmDomainModel> {
    return this.results.map {
        FilmDomainModel(
            title = it.title,
            episodeId = it.episode_id,
            openingCrawl = it.openingCrawl,
            director = it.director,
            producer = it.producer,
            releaseDate = it.release_date
        )
    }
}
