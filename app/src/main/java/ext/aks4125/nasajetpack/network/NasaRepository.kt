package ext.aks4125.nasajetpack.network

import javax.inject.Inject

class NasaRepository @Inject constructor(
    private val api: NasaApi
) {
    suspend fun searchQuery(query: String, page: Int) =
        api.searchQuery(query,"image",page)
}
