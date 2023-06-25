package ext.aks4125.nasajetpack.presentation.ui.detail

import androidx.compose.material3.Surface
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ext.aks4125.core.ui.theme.NasaJetpackTheme
import ext.aks4125.nasajetpack.data.NasaRepository
import ext.aks4125.nasajetpack.data.local.PlanetEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

const val TEST_ARGUMENT = "ID"

@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@ExperimentalCoroutinesApi
class DetailScreenKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Inject
    lateinit var repository: NasaRepository

    lateinit var viewModel: DetailViewModel
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        hiltRule.inject()
        savedStateHandle = SavedStateHandle()
        savedStateHandle[TEST_ARGUMENT] = "test"

        viewModel = DetailViewModel(repository, savedStateHandle)

        composeTestRule.setContent {
            NasaJetpackTheme {
                Surface {
                    PlanetDetail(
                        item = PlanetEntity(
                            "nasaId",
                            "12:12332:13:Z",
                            "Art of Moon",
                            "test description",
                            "test",
                        ),
                        formatDate = { arg -> arg },
                    )
                }
            }
        }
    }

    @Test
    fun testUiComponentsVisibility() {
        composeTestRule.onNodeWithTag("image")
            .assertExists()

        composeTestRule.onNodeWithTag("title")
            .assertExists()

        composeTestRule.onNodeWithTag("date")
            .assertExists()

        composeTestRule.onNodeWithTag("description")
            .assertExists()
    }

    @Test
    fun contentVerificationTest() {
        composeTestRule.onNodeWithTag("title")
            .assertTextEquals("Art of Moon")

        composeTestRule.onNodeWithTag("date")
            .assertTextEquals("12:12332:13:Z")

        composeTestRule.onNodeWithTag("description")
            .assertTextEquals("test description")
    }
}
