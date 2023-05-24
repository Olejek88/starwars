package de.olegrom.starwars.android.helper

import android.util.Log
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.olegrom.starwars.data.remote.dto.StarshipsDTO
import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.domain.domain_model.StarshipDomainModel
import de.olegrom.starwars.presentation.home.ListScreenState
import de.olegrom.starwars.presentation.home.ShipsViewModel
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.last

class ShipsListPagingSource(
    private val sharedViewModel: ShipsViewModel,
) : PagingSource<Int, StarshipDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, StarshipDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarshipDomainModel> {
        var nextPage: Int? = params.key ?: 1
        Log.d("[pager]", "load [${nextPage}]")
        val loadResult = sharedViewModel.loadShips(params.key ?: 1).last()
        // Empty flow == no load operations or unknown error
        if (loadResult is ListScreenState.Success) {
            val response = loadResult.entity as StarshipsDTO
            nextPage = if (response.nextPage == null) null else nextPage
            Log.d("[pager]", "response +++[${nextPage}]")
            return LoadResult.Page(
                data = response.asDomainModel(),
                prevKey = null,
                nextKey = response.nextPage
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