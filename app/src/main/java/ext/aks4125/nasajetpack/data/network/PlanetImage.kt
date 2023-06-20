package ext.aks4125.nasajetpack.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetImage(
    @field:Json(name = "href") var imageUrl: String? = null,
)
