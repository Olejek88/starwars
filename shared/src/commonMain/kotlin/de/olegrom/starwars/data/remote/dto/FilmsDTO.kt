package de.olegrom.starwars.data.remote.dto

import de.olegrom.starwars.domain.domain_model.FilmDomainModel

val filmsRegexPattern = """https:\/\/swapi.py4e.com\/api\/films\/\?page=([0-9]+)""".toRegex()

@kotlinx.serialization.Serializable
data class FilmsDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<FilmDTO>,
) {
    val currentPage: Int
        get() {
            if (previous==null) {
                return 1
            }
            val page = filmsRegexPattern.matchEntire(previous)?.groups?.get(0)?.value
            return page?.toInt() ?: 1
        }
    val nextPage: Int?
        get() {
            if (next==null) {
                return null
            }
            return try {
                val page =filmsRegexPattern.matchEntire(next)?.groups?.get(0)?.value
                page?.toInt()
            } catch (e: Exception) {
                1
            }
        }
}

@kotlinx.serialization.Serializable
data class FilmDTO(
    val title: String = "Undefined",
    var episode_id: Int = 2,
    var opening_crawl: String = "",
    var director: String = "",
    var producer: String = "",
    var release_date: String = "",
    var starships: List<String> = listOf(),
    var url: String = ""
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
            openingCrawl = this.opening_crawl,
            director = this.director,
            producer = this.producer,
            releaseDate = this.release_date,
            url = this.url
        )
}
