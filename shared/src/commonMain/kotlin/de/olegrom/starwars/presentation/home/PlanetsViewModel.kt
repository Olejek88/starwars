package de.olegrom.starwars.presentation.home

import de.olegrom.starwars.domain.usecase.lists.GetPlanetsUseCase
import de.olegrom.starwars.domain.util.Result
import de.olegrom.starwars.domain.util.asResult
import de.olegrom.starwars.util.asCommonFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last

class PlanetsViewModel(private val getPlanetsUseCase: GetPlanetsUseCase) : ViewModel() {
    fun loadPlanets(page: Int): Flow<ScreenState> = flow {
        when (val result = getPlanetsUseCase.invoke(page = page).asResult().last()) {
            is Result.Error -> {
                emit(ScreenState.Error(result.exception.message))
            }
            is Result.Success -> {
                emit(ScreenState.Success(result.data))
            }
        }
    }.asCommonFlow()
}
