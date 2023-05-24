package de.olegrom.starwars.android.helper

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.domain.domain_model.FilmDomainModel
import de.olegrom.starwars.presentation.home.FilmsScreenState
import de.olegrom.starwars.presentation.home.FilmsViewModel
import kotlinx.coroutines.flow.last

class FilmsListPagingSource(
    private val sharedViewModel: FilmsViewModel,
) : PagingSource<Int, FilmDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, FilmDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDomainModel> {
        var nextPage: Int? = params.key ?: 1
        Log.d("[list]","[$nextPage]")
        val result = sharedViewModel.loadMovies(params.key ?: 1).last()
        if (result is FilmsScreenState.Success) {
            val currentPage = result.response.currentPage
            nextPage = result.response.nextPage
            return LoadResult.Page(
                data = result.response.asDomainModel(),
                prevKey = null,
                nextKey = if (nextPage==currentPage || nextPage==null) null else nextPage
            )
        }
        if (result is FilmsScreenState.Error) {
            val error = result.errorMessage
            return LoadResult.Error(Throwable(error))
        }
        return LoadResult.Page(
            data = listOf(),
            prevKey = null,
            nextKey = null
        )
    }
}