package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.usecase.lists.GetFilmsUseCase
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.domain.util.Result
import de.olegrom.starwars.util.CommonFlow
import de.olegrom.starwars.util.asCommonFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.*

class FilmsViewModel(private val getFilmsUseCase: GetFilmsUseCase) : ViewModel() {
    fun loadMovies(page: Int): Flow<ListScreenState> = flow {
        when (val result = getFilmsUseCase.invoke(page = page).asResult().last()) {
            is Result.Error -> {
                emit(ListScreenState.Error(result.exception.message))
            }
            is Result.Idle -> {}
            is Result.Loading -> {}
            is Result.Success -> {
                emit(ListScreenState.Success(result.data))
            }
        }
    }.asCommonFlow()
}
