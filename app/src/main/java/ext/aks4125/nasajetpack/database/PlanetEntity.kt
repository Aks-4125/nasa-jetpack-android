package ext.aks4125.nasajetpack.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanetEntity constructor(
    @PrimaryKey
    val nasaId: String,
    val dateCreated: String,
    val title: String,
    val description: String,
    val imageUrl: String
)