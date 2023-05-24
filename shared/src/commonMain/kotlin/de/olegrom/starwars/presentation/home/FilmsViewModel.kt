package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.usecase.lists.GetFilmsUseCase
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.domain.util.Result
import de.olegrom.starwars.util.CommonFlow
import de.olegrom.starwars.util.asCommonFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.*

class FilmsViewModel(private val getFilmsUseCase: GetFilmsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<ListScreenState>(ListScreenState.Idle)
    var state = _state.asStateFlow()

    fun loadMovies(page: Int): Flow<FilmsScreenState> = flow {
        when (val result = getFilmsUseCase.invoke(page = page).asResult().last()) {
            is Result.Error -> {
                emit(FilmsScreenState.Error(result.exception.message))
            }
            is Result.Idle -> {}
            is Result.Loading -> {}
            is Result.Success -> {
                emit(FilmsScreenState.Success(result.data))
            }
        }
    }.asCommonFlow()
}
