package ext.aks4125.nasajetpack.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ext.aks4125.nasajetpack.data.network.PlanetInfo

open class NasaPagingSource(
    private val query: String,
    private val repository: NasaRepository,
) : PagingSource<Int, PlanetInfo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlanetInfo> {
        return try {
            val page = if (params.key != null && params.key!! <= 0) 1 else params.key ?: 1
            val response = repository.searchQuery(query, page)
            LoadResult.Page(
                data = response,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (response.isEmpty()) null else page.inc(),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlanetInfo>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
}
