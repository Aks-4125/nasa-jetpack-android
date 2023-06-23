package ext.aks4125.nasajetpack.presentation.ui.search


import ext.aks4125.nasajetpack.data.NasaPagingSource
import ext.aks4125.nasajetpack.data.NasaRepository
import ext.aks4125.nasajetpack.data.NasaRepositoryImpl
import ext.aks4125.nasajetpack.data.local.AppDatabase
import ext.aks4125.nasajetpack.data.network.CollectData
import ext.aks4125.nasajetpack.data.network.NasaApi
import ext.aks4125.nasajetpack.data.network.NasaModel
import ext.aks4125.nasajetpack.data.network.Planet
import ext.aks4125.nasajetpack.data.network.PlanetImage
import ext.aks4125.nasajetpack.data.network.PlanetInfo
import ext.aks4125.nasajetpack.util.InstantExecutorExtension
import ext.aks4125.nasajetpack.util.MainDispatcherRule
import ext.aks4125.nasajetpack.util.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
internal class SearchViewModelTest2 {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainDispatcherRule()


    private var repository: NasaRepository = mockk()


    private var appDatabase: AppDatabase = mockk()

    @MockK
    private lateinit var pagingSource: NasaPagingSource

    private lateinit var viewModel: SearchViewModel

    private val api: NasaApi = mockk()

    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    val list = listOf(PlanetInfo("idate", "desc", "id", "title", "url"))

    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `search with query success`() = runBlocking {
        coEvery {
            appDatabase.nasaDao.getPlanetDetails(id = "KSC-20150402-PH")
        } coAnswers {
            TestData.testDataPlanetEntity()
        }

        repository = NasaRepositoryImpl(api, appDatabase)

        coEvery {
            repository.getPlanetDetail("test")
        } coAnswers { TestData.testDataPlanetEntity() }


        val entity = repository.getPlanetDetail(nasaId = "KSC-20150402-PH");

        Assert.assertNotNull(entity)



        viewModel = SearchViewModel(repository)


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


        coEvery {
            repository.searchQuery("test", any())
        } coAnswers {
            list
        }
        viewModel.query.value = "test"

        Assert.assertTrue(viewModel.query.value.isNotEmpty())

      //  viewModel.invalidateDataSource()

    }
}