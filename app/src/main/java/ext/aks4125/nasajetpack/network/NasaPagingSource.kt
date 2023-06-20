package ext.aks4125.nasajetpack.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ext.aks4125.nasajetpack.data.Planet
import ext.aks4125.nasajetpack.data.PlanetInfo

class NasaPagingSource(
    private val query: String,
    private val repository: NasaRepository
) : PagingSource<Int, PlanetInfo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlanetInfo> {
        return try {
            val page = if (params.key != null && params.key!! <= 0) 1 else params.key ?: 1

            Log.e("okhttp --", "query  = $query -- page = $page")
            val response: List<Planet> =
                repository.searchQuery(query, page).collection?.items ?: arrayListOf()

            val mergeList: List<PlanetInfo> = response.map {
                it.itemList?.first()?.copy(imageUrl = it.image?.first()?.imageUrl) ?: PlanetInfo()
            }.toList()

            //todo remove log
            Log.e("okhttp --", "response size = ${response.size}")


            Log.e("okhttp --", "list size = ${mergeList.size}")
            LoadResult.Page(
                data = mergeList,
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
