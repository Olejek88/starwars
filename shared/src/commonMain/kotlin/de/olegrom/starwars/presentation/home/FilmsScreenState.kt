package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.data.remote.dto.FilmsDTO

sealed interface FilmsScreenState{
    object Loading: FilmsScreenState
    object Idle : FilmsScreenState
    data class Success(val response: FilmsDTO) : FilmsScreenState
    data class Error(val errorMessage: String) : FilmsScreenState
}

