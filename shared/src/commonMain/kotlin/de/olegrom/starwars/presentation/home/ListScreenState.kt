package de.olegrom.starwars.presentation.home

sealed interface ListScreenState{
    object Loading: ListScreenState
    object Idle : ListScreenState
    data class Success(val entities: List<Any>) : ListScreenState
    data class Error(val errorMessage: String) : ListScreenState
}

