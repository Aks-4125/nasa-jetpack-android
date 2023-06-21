package ext.aks4125.nasajetpack.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import ext.aks4125.nasajetpack.data.NasaRepository
import ext.aks4125.nasajetpack.util.MainDispatcherRule
import ext.aks4125.nasajetpack.util.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat

const val EMPTY = ""
const val TEST_ARGUMENT = "ID"

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainDispatcherRule()

    private var repository = mockk<NasaRepository>()

    private lateinit var viewModel: DetailViewModel
    private lateinit var savedStateHandle: SavedStateHandle


    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

    }

    @Before
    fun before() {
        initViewModel()
    }

    fun tearDown() {
        unmockkAll()
    }

    private fun initViewModel() {
        savedStateHandle = SavedStateHandle()
        savedStateHandle[TEST_ARGUMENT] = "test"

        coEvery {
            repository.getPlanetDetail("test")
        } coAnswers { TestData.testDataPlanetEntity() }

        viewModel = DetailViewModel(repository, savedStateHandle)
    }

    @Test
    fun `viewModel UiState test`() = runBlocking {
        viewModel.uiState.item.let {
            assertEquals(it?.nasaId, TestData.testDataPlanetEntity().nasaId)
            assertEquals(it?.title, TestData.testDataPlanetEntity().title)
            assertEquals(it?.description, TestData.testDataPlanetEntity().description)
        }

        coVerify(atLeast = 1) { repository.getPlanetDetail(any()) }
    }


    @Test
    fun `format date test`() = runBlocking {
        assertEquals(
            "01.08.2014 00:00", viewModel.formatDate(TestData.testDataPlanetEntity().dateCreated)
        )
    }


    @Test
    fun `format date null empty test`() = runBlocking {
        assertEquals(
            EMPTY,
            viewModel.formatDate(EMPTY)
        )
    }
}
