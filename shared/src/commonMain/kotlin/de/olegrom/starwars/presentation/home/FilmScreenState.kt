package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.domain_model.FilmDomainModel

sealed interface FilmScreenState{
    object Loading: FilmScreenState
    object Idle : FilmScreenState
    data class Success(val film: FilmDomainModel) : FilmScreenState
    data class Error(val errorMessage: String) : FilmScreenState
}

