package ext.aks4125.nasajetpack

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ext.aks4125.nasajetpack.ui.components.ScaffoldWithTopBar
import ext.aks4125.nasajetpack.ui.detail.DetailScreen
import ext.aks4125.nasajetpack.ui.search.SearchScreen

@ExperimentalMaterial3Api
@Composable
fun NasaNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NasaDestinations.LANDING_PAGE_SEARCH,
    finish: () -> Unit
) {
    Scaffold(
        topBar = {
            ScaffoldWithTopBar(navController) { finish() }
        }) { padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = androidx.compose.ui.Modifier.padding(padding)
        ) {
            composable(NasaDestinations.LANDING_PAGE_SEARCH) {
                SearchScreen(navController)
            }
            composable(NasaDestinations.DETAIL) {
                DetailScreen(navController)
            }
        }
    }
}