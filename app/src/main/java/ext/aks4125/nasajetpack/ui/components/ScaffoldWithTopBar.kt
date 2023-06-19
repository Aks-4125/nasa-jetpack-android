package ext.aks4125.nasajetpack.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import ext.aks4125.nasajetpack.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(
    navController: NavHostController,
    finish: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if (!navController.navigateUp())
                    finish()
            }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    )
}