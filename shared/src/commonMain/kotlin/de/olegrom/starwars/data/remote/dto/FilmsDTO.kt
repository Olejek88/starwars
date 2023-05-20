package de.olegrom.starwars.data.remote.dto

import de.olegrom.starwars.domain.domain_model.FilmDomainModel

@kotlinx.serialization.Serializable
data class FilmsDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<FilmDTO>,
)

@kotlinx.serialization.Serializable
data class FilmDTO(
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
        it.asDomainModel()
    }
}

fun FilmDTO.asDomainModel(): FilmDomainModel {
    return FilmDomainModel(
            title = this.title,
            episodeId = this.episode_id,
            openingCrawl = this.openingCrawl,
            director = this.director,
            producer = this.producer,
            releaseDate = this.release_date
        )
}
