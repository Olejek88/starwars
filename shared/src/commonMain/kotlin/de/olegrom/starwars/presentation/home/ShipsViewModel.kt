package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.data.remote.dto.StarshipsDTO
import de.olegrom.starwars.domain.usecase.lists.GetStarshipsUseCase
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.domain.util.Result
import de.olegrom.starwars.util.asCommonFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.*

class ShipsViewModel(private val getStarshipsUseCase: GetStarshipsUseCase) : ViewModel() {
    private var _lastPage = 0
    private var _lastStarShip = StarshipsDTO(count=0, next = null, previous = null, results = listOf())

    fun loadShips(page: Int): Flow<ListScreenState> = flow {
        if (page != _lastPage) {
            _lastPage = page
            when (val result = getStarshipsUseCase.invoke(page = page).asResult().last()) {
                is Result.Error -> {
                    emit(ListScreenState.Error(result.exception.message))
                }
                is Result.Idle -> {}
                is Result.Loading -> {
                    emit(ListScreenState.Loading)
                }
                is Result.Success -> {
                    _lastStarShip = result.data
                    emit(ListScreenState.Success(result.data))
                }
            }
        } else {
            emit(ListScreenState.Success(_lastStarShip))
        }
    }.asCommonFlow()
}
