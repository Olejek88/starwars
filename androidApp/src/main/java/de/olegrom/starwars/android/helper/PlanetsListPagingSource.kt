package de.olegrom.starwars.android.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.olegrom.starwars.data.remote.dto.PlanetsDTO
import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.domain.domain_model.PlanetDomainModel
import de.olegrom.starwars.presentation.home.ScreenState
import de.olegrom.starwars.presentation.home.PlanetsViewModel
import kotlinx.coroutines.flow.last

class PlanetsListPagingSource(
    private val sharedViewModel: PlanetsViewModel,
) : PagingSource<Int, PlanetDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, PlanetDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlanetDomainModel> {
        return when(val loadResult = sharedViewModel.loadPlanets(params.key ?: 1).last()) {
            is ScreenState.Success -> {
                val response = loadResult.entity as PlanetsDTO
                LoadResult.Page(
                    data = response.asDomainModel(),
                    prevKey = null,
                    nextKey = response.nextPage ?: ((params.key ?: 1) + 1)
                )
            }
            is ScreenState.Error -> {
                val error = loadResult.errorMessage
                LoadResult.Error(Throwable(error))
            }
        }
    }
}