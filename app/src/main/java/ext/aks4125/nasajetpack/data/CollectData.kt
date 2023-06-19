package ext.aks4125.nasajetpack.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectData(
    @field:Json(name = "items") var items: List<Planet>? = null,
)
