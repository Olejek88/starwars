package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.domain_model.FilmDomainModel

sealed interface FilmsScreenState{
    object Loading: FilmsScreenState
    object Idle : FilmsScreenState
    data class Success(val films: List<FilmDomainModel>) : FilmsScreenState
    data class Error(val errorMessage: String) : FilmsScreenState
}

