package ext.aks4125.nasajetpack.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NasaModel(
    @field:Json(name = "collection") var collection: CollectData? = null
)
