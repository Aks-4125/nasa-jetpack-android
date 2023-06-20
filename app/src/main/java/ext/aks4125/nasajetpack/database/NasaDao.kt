package ext.aks4125.nasajetpack.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface NasaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(planets: List<PlanetEntity>)

    @Query("select * from PlanetEntity WHERE nasaId LIKE :id")
    fun getPlanetDetails(id: String): Flow<PlanetEntity?>

    @Query("Delete From PlanetEntity")
    suspend fun clearAll()
}

@Database(entities = [PlanetEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val usersDao: NasaDao
}