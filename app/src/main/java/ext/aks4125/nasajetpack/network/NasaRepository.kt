package ext.aks4125.nasajetpack.network

import android.util.Log
import ext.aks4125.nasajetpack.data.PlanetInfo
import ext.aks4125.nasajetpack.data.asDatabaseModel
import ext.aks4125.nasajetpack.database.AppDatabase
import ext.aks4125.nasajetpack.database.PlanetEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class NasaRepository @Inject constructor(
    private val api: NasaApi,
    private val appDatabase: AppDatabase
) {
    suspend fun searchQuery(query: String, page: Int): List<PlanetInfo> {
        val response = api.searchQuery(query, "image", page)
        Log.e("okhttp --", "query  = $query -- page = $page")

        val items = response.collection?.items ?: arrayListOf()

        val mergeList: List<PlanetInfo> = items.map {
            it.itemList?.first()?.copy(imageUrl = it.image?.first()?.imageUrl) ?: PlanetInfo(
                nasaId = UUID.randomUUID().toString()
            )
        }.toList()

        insertData(mergeList.asDatabaseModel())
        return mergeList
    }

    private suspend fun insertData(list: List<PlanetEntity>) {
        try {
            appDatabase.usersDao.insertData(list)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getPlanetDetail(nasaId: String): PlanetEntity? =
        appDatabase.usersDao.getPlanetDetails(nasaId)

}
