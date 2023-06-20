package ext.aks4125.nasajetpack.data.network

import ext.aks4125.nasajetpack.data.network.NasaModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("/search")
    suspend fun searchQuery(
        @Query("q") query: String,
        @Query("media_type") type: String,
        @Query("page") startIndex: Int,
    ): NasaModel

}