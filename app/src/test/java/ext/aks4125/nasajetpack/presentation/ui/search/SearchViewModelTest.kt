package ext.aks4125.nasajetpack.presentation.ui.search

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ext.aks4125.nasajetpack.data.NasaPagingSource
import ext.aks4125.nasajetpack.data.network.PlanetInfo
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
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
internal class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainDispatcherRule()

    private var repository = mockk<FakeMyRepository>()

    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `search with query success`() = runBlocking {

        viewModel = SearchViewModel(repository = repository)


        val pagingSource = NasaPagingSource(
            "test",
            repository = repository
        )

        coEvery {
            repository.searchQuery("test", any())
        } coAnswers {
            TestData.testDataPlanetInfoList()
        }
        viewModel.setQuery("test")


        val params = PagingSource
            .LoadParams
            .Refresh(
                key = 1,
                loadSize = 1,
                placeholdersEnabled = false
            )

        val expected: PagingSource.LoadResult.Page<Int, PlanetInfo> = PagingSource
            .LoadResult
            .Page(
                data = TestData.testDataPlanetInfoList(),
                prevKey = null,
                nextKey = 2
            )

        // when
        val actual: PagingSource.LoadResult<Int, PlanetInfo> = pagingSource.load(params = params)

        assertEquals(
            0, pagingSource.getRefreshKey(
                PagingState(
                    listOf(),
                    1,
                    PagingConfig(pageSize = 50),
                    10
                )
            )
        )

        assertEquals(expected, actual)

        Assert.assertTrue(viewModel.query.value.isNotEmpty())

    }

    @Test
    fun `search with query error test`() = runBlocking {

        val pagingSource = NasaPagingSource(
            "test",
            repository = repository
        )

        coEvery {
            repository.searchQuery("test", any())
        } coAnswers {
            throw RuntimeException()
        }

        val viewModel = SearchViewModel(repository = repository)

        viewModel.setQuery("test")

        val params = PagingSource
            .LoadParams
            .Refresh(
                key = 1,
                loadSize = 1,
                placeholdersEnabled = false
            )

        val expected: PagingSource.LoadResult.Error<Any, Any> = PagingSource
            .LoadResult
            .Error(RuntimeException())

        // when
        val actual: PagingSource.LoadResult<Int, PlanetInfo> = pagingSource.load(params = params)

        assertEquals(expected, actual)

    }


}