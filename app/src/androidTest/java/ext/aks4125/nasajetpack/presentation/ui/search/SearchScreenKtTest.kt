package ext.aks4125.nasajetpack.presentation.ui.search

import androidx.compose.material3.Surface
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.test.espresso.action.ViewActions.click
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ext.aks4125.core.ui.theme.NasaJetpackTheme
import ext.aks4125.nasajetpack.data.NasaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SearchScreenKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Inject
    lateinit var repository: NasaRepository

    lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = SearchViewModel(repository)
        viewModel.setQuery("Sun")
        composeTestRule.setContent {
            NasaJetpackTheme {
                Surface {
                    SearchScreen(
                        viewModel = viewModel,
                        navigateToDetail = { it }
                    )
                }
            }
        }
    }

    @After
    fun tearDown() {

    }

    @Test
    fun searchViewVisibilityTest() {
        composeTestRule.onNodeWithText("Search").assertExists()
    }

    @Test
    fun displayAndSearchUiTest() {

        composeTestRule
            .onNodeWithTag("search")
            .performTouchInput { click() }
    }

    @Test
    fun exitTestAppBarBackButton() {

        composeTestRule
            .onNode(hasTestTag("search"))
            .performTouchInput { click() }
            .assertContentDescriptionEquals("Search to get planet details")
            .performTextInput("moon")

        composeTestRule
            .onNode(hasTestTag("search"))
            .performTouchInput { click() }

    }

}