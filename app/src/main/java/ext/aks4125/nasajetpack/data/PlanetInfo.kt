package ext.aks4125.nasajetpack.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetInfo(
    @field:Json(name = "date_created") var dateCreated: String? = null,
    @field:Json(name = "description") var description: String? = null,
    @field:Json(name = "nasa_id") var nasaId: String? = null,
    @field:Json(name = "title") var title: String? = null,
    var imageUrl: String? = null
)
