package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.usecase.lists.GetPersonsUseCase
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.domain.util.Result
import de.olegrom.starwars.util.asCommonFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PersonsViewModel(private val getPersonsUseCase: GetPersonsUseCase) : ViewModel() {
    fun loadPersons(page: Int): Flow<ListScreenState> = flow {
        when (val result = getPersonsUseCase.invoke(page = page).asResult().last()) {
            is Result.Error -> {
                emit(ListScreenState.Error(result.exception.message))
            }
            is Result.Idle -> {}
            is Result.Loading -> {
                emit(ListScreenState.Loading)
            }
            is Result.Success -> {
                emit(ListScreenState.Success(result.data))
            }
        }
    }.asCommonFlow()
}
