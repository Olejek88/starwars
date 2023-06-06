package de.olegrom.starwars.android.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.olegrom.starwars.data.remote.dto.PersonsDTO
import de.olegrom.starwars.data.remote.dto.asDomainModel
import de.olegrom.starwars.domain.domain_model.PersonDomainModel
import de.olegrom.starwars.presentation.home.ScreenState
import de.olegrom.starwars.presentation.home.PersonsViewModel
import kotlinx.coroutines.flow.last

class PersonsListPagingSource(
    private val sharedViewModel: PersonsViewModel,
) : PagingSource<Int, PersonDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, PersonDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonDomainModel> {
        return when(val loadResult = sharedViewModel.loadPersons(params.key ?: 1).last()) {
            is ScreenState.Success -> {
                val response = loadResult.entity as PersonsDTO
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