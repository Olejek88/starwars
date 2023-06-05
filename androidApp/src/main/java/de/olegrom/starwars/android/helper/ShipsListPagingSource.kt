package de.olegrom.starwars.android.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.olegrom.starwars.data.remote.dto.StarshipsDTO
import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel
import de.olegrom.starwars.presentation.home.ListScreenState
import de.olegrom.starwars.presentation.home.ShipsViewModel
import kotlinx.coroutines.flow.last

class ShipsListPagingSource(
    private val sharedViewModel: ShipsViewModel,
) : PagingSource<Int, StarshipDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, StarshipDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarshipDomainModel> {
        return when(val loadResult = sharedViewModel.loadShips(params.key ?: 1).last()) {
            is ListScreenState.Success -> {
                val response = loadResult.entity as StarshipsDTO
                LoadResult.Page(
                    data = response.asDomainModel(),
                    prevKey = null,
                    nextKey = response.nextPage ?: ((params.key ?: 1) + 1)
                )
            }
            is ListScreenState.Error -> {
                val error = loadResult.errorMessage
                LoadResult.Error(Throwable(error))
            }
        }
    }
}