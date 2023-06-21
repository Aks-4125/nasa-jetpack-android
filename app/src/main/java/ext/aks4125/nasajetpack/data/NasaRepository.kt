package ext.aks4125.nasajetpack.data

import ext.aks4125.nasajetpack.data.local.PlanetEntity
import ext.aks4125.nasajetpack.data.network.PlanetInfo

interface NasaRepository {
    suspend fun searchQuery(query: String, page: Int): List<PlanetInfo>

    suspend fun getPlanetDetail(nasaId: String): PlanetEntity?
}
