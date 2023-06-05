package de.olegrom.starwars.android.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.olegrom.starwars.data.remote.dto.PlanetsDTO
import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.domain.domain_model.PlanetDomainModel
import de.olegrom.starwars.presentation.home.ListScreenState
import de.olegrom.starwars.presentation.home.PlanetsViewModel
import kotlinx.coroutines.flow.last

class PlanetsListPagingSource(
    private val sharedViewModel: PlanetsViewModel,
) : PagingSource<Int, PlanetDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, PlanetDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlanetDomainModel> {
        val loadResult = sharedViewModel.loadPlanets(params.key ?: 1).last()
        if (loadResult is ListScreenState.Success) {
            val response = loadResult.entity as PlanetsDTO
            return LoadResult.Page(
                data = response.asDomainModel(),
                prevKey = null,
                nextKey = response.nextPage ?: ((params.key ?: 1) + 1)
            )
        }
        if (loadResult is ListScreenState.Error) {
            val error = loadResult.errorMessage
            return LoadResult.Error(Throwable(error))
        }
        return LoadResult.Page(
            data = listOf(),
            prevKey = null,
            nextKey = null
        )
    }
}