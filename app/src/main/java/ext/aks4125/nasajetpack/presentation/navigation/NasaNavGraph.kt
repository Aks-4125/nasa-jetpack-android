package ext.aks4125.nasajetpack.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ext.aks4125.core.ui.components.ScaffoldWithTopBar
import ext.aks4125.nasajetpack.presentation.ui.detail.DetailScreen
import ext.aks4125.nasajetpack.presentation.ui.search.SearchScreen
import ext.aks4125.nasajetpack.presentation.ui.search.SearchViewModel
import ext.aks4125.nasajetpack.presentation.util.*

/**
 * Composable function that defines the navigation graph of the NASA Jetpack app.
 *
 * @param navController The [NavHostController] that manages the navigation within the app.
 * @param startDestination The start destination of the navigation graph. Defaults to [NasaDestinations.LANDING_PAGE_SEARCH].
 * @param finish A callback function to be invoked when the user wants to finish or exit the app.
 */
@ExperimentalMaterial3Api
@Composable
fun NasaNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NasaDestinations.LANDING_PAGE_SEARCH,
    finish: () -> Unit,
) {


    val viewModel: SearchViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            ScaffoldWithTopBar(navController) { finish() }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(padding),
        ) {
            composable(NasaDestinations.LANDING_PAGE_SEARCH) {
                SearchScreen(viewModel) {
                    navController.navigate(NasaDestinations.DETAIL + "/$it")
                }
            }
            composable(
                route = "${NasaDestinations.DETAIL}/{${NasaDestinationsArgs.ID}}",
                arguments = listOf(
                    navArgument(NasaDestinationsArgs.ID) {
                        type = NavType.StringType
                    },
                ),
            ) {
                DetailScreen()
            }
        }
    }
}