package ext.aks4125.nasajetpack.presentation.ui.search

/**

This file contains the implementation of the SearchViewModel class.
The SearchViewModel is responsible for handling the search functionality and managing the data flow
between the UI layer and the data layer.
 */
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ext.aks4125.nasajetpack.data.NasaPagingSource
import ext.aks4125.nasajetpack.data.NasaRepository
import ext.aks4125.nasajetpack.data.network.PlanetInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_MAX = 100

/**

The SearchViewModel class is a ViewModel that handles the search functionality.

It interacts with the NasaRepository to retrieve search results.

@param repository The NasaRepository instance used to retrieve search results.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NasaRepository,
) : ViewModel() {

    /**

     The current search query.
     */
    var query = mutableStateOf("")
        private set
    private lateinit var pagingSource: NasaPagingSource

    /**

     Flow of PagingData that represents the search results.
     */
    val itemPager: Flow<PagingData<PlanetInfo>> = Pager(config = PagingConfig(PAGE_MAX)) {
        NasaPagingSource(query.value, repository).also { pagingSource = it }
    }
        .flow
        .cachedIn(viewModelScope)

    /**

     Sets the search query.
     @param query The new search query.
     */
    fun setQuery(query: String) {
        this.query.value = query
    }

    /**
     Invalidates the data source.
     This method is called to refresh the search results.
     */
    fun invalidateDataSource() {
        pagingSource.invalidate()
    }
}
