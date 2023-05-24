package de.olegrom.starwars.android.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.olegrom.starwars.data.remote.dto.*
import de.olegrom.starwars.domain.domain_model.PersonDomainModel
import de.olegrom.starwars.presentation.home.ListScreenState
import de.olegrom.starwars.presentation.home.PersonsViewModel
import kotlinx.coroutines.flow.last

class PersonsListPagingSource(
    private val sharedViewModel: PersonsViewModel,
) : PagingSource<Int, PersonDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, PersonDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonDomainModel> {
        val loadResult = sharedViewModel.loadPersons(params.key ?: 1).last()
        if (loadResult is ListScreenState.Success) {
            val response = loadResult.entity as PersonsDTO
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