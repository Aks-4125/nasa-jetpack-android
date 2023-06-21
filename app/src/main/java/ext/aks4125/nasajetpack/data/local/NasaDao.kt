package ext.aks4125.nasajetpack.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface NasaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(planets: List<PlanetEntity>)

    @Query("select * from PlanetEntity WHERE nasaId LIKE :id")
    suspend fun getPlanetDetails(id: String): PlanetEntity?

    @Query("Delete From PlanetEntity")
    suspend fun clearAll()
}

@Database(entities = [PlanetEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract val nasaDao: NasaDao
}