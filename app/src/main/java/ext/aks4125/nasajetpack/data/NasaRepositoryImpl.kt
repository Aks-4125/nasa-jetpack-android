package ext.aks4125.nasajetpack.data

import ext.aks4125.nasajetpack.data.local.AppDatabase
import ext.aks4125.nasajetpack.data.local.PlanetEntity
import ext.aks4125.nasajetpack.data.network.NasaApi
import ext.aks4125.nasajetpack.data.network.PlanetInfo
import ext.aks4125.nasajetpack.data.network.asDatabaseModel
import java.util.UUID
import javax.inject.Inject

class NasaRepositoryImpl @Inject constructor(
    private val api: NasaApi,
    private val appDatabase: AppDatabase,
) : NasaRepository {

    override suspend fun searchQuery(query: String, page: Int): List<PlanetInfo> {
        val response = api.searchQuery(query, "image", page)

        val items = response.collection?.items ?: arrayListOf()

        val mergeList: List<PlanetInfo> = items.map {
            it.itemList?.first()?.copy(imageUrl = it.image?.first()?.imageUrl) ?: PlanetInfo(
                nasaId = UUID.randomUUID().toString(),
            )
        }.toList()

        insertData(mergeList.asDatabaseModel())
        return mergeList
    }

    private suspend fun insertData(list: List<PlanetEntity>) {
        try {
            appDatabase.nasaDao.insertData(list)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getPlanetDetail(nasaId: String): PlanetEntity? =
        appDatabase.nasaDao.getPlanetDetails(nasaId)
}
