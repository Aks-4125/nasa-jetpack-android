package ext.aks4125.nasajetpack.presentation.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ext.aks4125.nasajetpack.data.network.PlanetInfo
import ext.aks4125.nasajetpack.data.NasaPagingSource
import ext.aks4125.nasajetpack.data.NasaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
private const val PAGE_MAX = 100
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NasaRepository,
) : ViewModel() {

    var query = mutableStateOf("")
        private set

    private lateinit var pagingSource: NasaPagingSource

    val itemPager: Flow<PagingData<PlanetInfo>> = Pager(config = PagingConfig(PAGE_MAX)) {
        NasaPagingSource(query.value, repository).also { pagingSource = it }
    }.flow.cachedIn(viewModelScope)

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun invalidateDataSource() {
        pagingSource.invalidate()
    }
}

