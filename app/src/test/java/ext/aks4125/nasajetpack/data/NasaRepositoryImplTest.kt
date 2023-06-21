package ext.aks4125.nasajetpack.data

import ext.aks4125.nasajetpack.data.local.AppDatabase
import ext.aks4125.nasajetpack.data.local.NasaDao
import ext.aks4125.nasajetpack.data.network.CollectData
import ext.aks4125.nasajetpack.data.network.NasaApi
import ext.aks4125.nasajetpack.data.network.NasaModel
import ext.aks4125.nasajetpack.data.network.Planet
import ext.aks4125.nasajetpack.data.network.PlanetImage
import ext.aks4125.nasajetpack.util.InstantExecutorExtension
import ext.aks4125.nasajetpack.util.MainDispatcherRule
import ext.aks4125.nasajetpack.util.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class NasaRepositoryImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainDispatcherRule()


    private val api: NasaApi = mockk()
    private val appDatabase: AppDatabase = mockk()

    private val nasaDao = mockk<NasaDao>()


    private lateinit var repository: NasaRepositoryImpl

    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `search with success`() = runBlocking {
        repository = NasaRepositoryImpl(api, appDatabase)

        coEvery {
            api.searchQuery(query = "test", type = "image", startIndex = 1)
        } coAnswers {
            NasaModel(
                CollectData(
                    listOf(
                        Planet(
                            TestData.testDataPlanetInfoList(),
                            listOf(
                                PlanetImage()
                            )
                        )
                    )
                )
            )
        }

        val data = repository.searchQuery(query = "test", page = 1)

        Assert.assertTrue(data.isNotEmpty())
        Assert.assertEquals(data[0].nasaId, TestData.testDataPlanetInfoList()[0].nasaId)
        Assert.assertNotNull(data)

        /**
         * Test - iterative line branch test to cover all area e.g it.itemList?.first()?
         */

        coEvery {
            api.searchQuery(query = "test", type = "image", startIndex = 1)
        } coAnswers {
            NasaModel(
                CollectData(
                    listOf(
                        Planet(
                           null, // list?.first() should return null
                            emptyList()
                        )
                    )
                )
            )
        }

        val emptyData = repository.searchQuery(query = "test", page = 1)
        Assert.assertNotNull(emptyData)

    }

    @Test
    fun `getPlanetDetail Test`() = runBlocking {
        repository = NasaRepositoryImpl(api, appDatabase)

        coEvery {
            appDatabase.nasaDao.getPlanetDetails(id = "KSC-20150402-PH")
        } coAnswers {
            TestData.testDataPlanetEntity()
        }


        val entity = repository.getPlanetDetail(nasaId = "KSC-20150402-PH");

        Assert.assertNotNull(entity)
    }

}