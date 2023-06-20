package ext.aks4125.nasajetpack.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Planet(
    @field:Json(name = "data") var itemList: List<PlanetInfo>? = null,
    @field:Json(name = "links") var image: List<PlanetImage>? = null
)
