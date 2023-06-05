package de.olegrom.starwars.android.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.olegrom.starwars.data.remote.dto.FilmsDTO
import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.presentation.home.FilmsViewModel
import de.olegrom.starwars.presentation.home.ListScreenState
import kotlinx.coroutines.flow.last

class FilmsListPagingSource(
    private val sharedViewModel: FilmsViewModel,
) : PagingSource<Int, FilmDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, FilmDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDomainModel> {
        val nextPage: Int = params.key ?: 1
        val loadResult = sharedViewModel.loadMovies(nextPage).last()
        if (loadResult is ListScreenState.Success) {
            val response = loadResult.entity as FilmsDTO
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