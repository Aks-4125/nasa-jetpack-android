package ext.aks4125.nasajetpack.util

import ext.aks4125.nasajetpack.data.local.PlanetEntity
import ext.aks4125.nasajetpack.data.network.PlanetInfo

object TestData {
    fun testDataPlanetInfoList() = listOf(
        PlanetInfo(
            dateCreated = "2014-08-01T00:00:00Z",
            description = "Interior View of Hangar M Annex",
            nasaId = "KSC-20150402-PH",
            title = "KSC-20150402-PH-PR_17hangM",
            imageUrl = "thumb.jpg",
        ),
    )

    fun testDataPlanetEntity() = PlanetEntity(
        dateCreated = "2014-08-01T00:00:00Z",
        description = "Interior View of Hangar M Annex",
        nasaId = "KSC-20150402-PH",
        title = "KSC-20150402-PH-PR_17hangM",
        imageUrl = "thumb.jpg",
    )
}
