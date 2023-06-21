package ext.aks4125.nasajetpack.presentation.ui.search

import ext.aks4125.nasajetpack.data.NasaRepository
import ext.aks4125.nasajetpack.data.local.PlanetEntity
import ext.aks4125.nasajetpack.data.network.PlanetInfo
import ext.aks4125.nasajetpack.util.TestData
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class FakeMyRepository() : NasaRepository {

    override suspend fun searchQuery(query: String, page: Int): List<PlanetInfo> {
        return TestData.testDataPlanetInfoList()
    }

    override suspend fun getPlanetDetail(nasaId: String): PlanetEntity {
        return TestData.testDataPlanetEntity()
    }

}