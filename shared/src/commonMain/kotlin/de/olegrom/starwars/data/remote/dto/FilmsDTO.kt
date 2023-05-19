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
    val title: String? = "Undefined"
)

fun FilmsDTO.asDomainModel(): List<FilmDomainModel> {
    return this.results.map {
        FilmDomainModel(
            title = it.title?: "Undefined"
        )
    }
}
