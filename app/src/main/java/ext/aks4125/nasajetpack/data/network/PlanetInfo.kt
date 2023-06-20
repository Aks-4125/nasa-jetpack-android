package ext.aks4125.nasajetpack.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ext.aks4125.nasajetpack.data.local.PlanetEntity

@JsonClass(generateAdapter = true)
data class PlanetInfo(
    @field:Json(name = "date_created") var dateCreated: String? = null,
    @field:Json(name = "description") var description: String? = null,
    @field:Json(name = "nasa_id") var nasaId: String,
    @field:Json(name = "title") var title: String? = null,
    var imageUrl: String? = null
)

fun List<PlanetInfo>.asDatabaseModel(): List<PlanetEntity> {
    return map {
        PlanetEntity(
            nasaId = it.nasaId,
            dateCreated = it.dateCreated.orEmpty(),
            title = it.title.orEmpty(),
            description =  it.description.orEmpty(),
            imageUrl = it.imageUrl.orEmpty()
        )
    }
}
