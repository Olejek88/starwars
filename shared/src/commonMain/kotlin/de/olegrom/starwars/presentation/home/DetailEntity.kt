package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel

sealed class DetailEntity<T>(val value: T) {
    class Film(value: FilmDomainModel) : DetailEntity<FilmDomainModel>(value)
    class Starship(value: StarshipDomainModel) : DetailEntity<StarshipDomainModel>(value)
}