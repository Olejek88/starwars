package de.olegrom.starwars.data.remote.dto

import de.olegrom.starwars.domain.domain_model.FilmDomainModel

@kotlinx.serialization.Serializable
data class FilmsDTO(
    val films: List<Film>,
    )

@kotlinx.serialization.Serializable
data class Source(
    val id: String? = "",
    val name: String
)

@kotlinx.serialization.Serializable
data class Film(
    val title: String? = "Undefined"
)

fun FilmsDTO.asDomainModel(): List<FilmDomainModel> {
    return this.films.map {
           FilmDomainModel(
               title = it.title?: "Undefined"
           )
    }
}