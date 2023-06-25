package ext.aks4125.nasajetpack.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ext.aks4125.core.ui.theme.NasaJetpackTheme
import ext.aks4125.nasajetpack.R
import ext.aks4125.nasajetpack.data.network.PlanetInfo
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_4

/**

Composable function that represents the search screen UI.

@param viewModel The SearchViewModel instance used to handle search functionality.

@param navigateToDetail Callback function for navigating to the detail screen.
 */
@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToDetail: (String) -> Unit,
) {
    val items = viewModel.itemPager.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background),
    ) {
        CustomSearchBar(viewModel)
        // lazy load items
        ListItem(item = items, viewModel = viewModel, navigateToDetail = navigateToDetail)
    }
}

/**

Composable function that represents the custom search bar UI.
@param viewModel The SearchViewModel instance used to handle search functionality.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CustomSearchBar(viewModel: SearchViewModel) {
    var active by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .testTag("search")
                .semantics { this.contentDescription = "Search to get planet details" },
            query = viewModel.query.value,
            onQueryChange = {
                viewModel.query.value = it
            },
            onSearch = {
                viewModel.invalidateDataSource()
                active = false
                viewModel.setQuery(it)
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(
                    modifier = Modifier.testTag("input"),
                    style = MaterialTheme.typography.labelMedium,
                    text = "Search",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                )
            },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { viewModel.setQuery("") }) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null,
                    )
                }
            },
            content = {},
        )

        Spacer(modifier = Modifier.height(dimen_4))
    }
}

/**
Composable function that represents the list of search results.

@param item The LazyPagingItems representing the search results.

@param viewModel The SearchViewModel instance used to handle search functionality.

@param navigateToDetail Callback function for navigating to the detail screen.
 */
@Composable
fun ListItem(
    item: LazyPagingItems<PlanetInfo>,
    viewModel: SearchViewModel,
    navigateToDetail: (String) -> Unit,
) {
    if (viewModel.query.value.isNotEmpty()) {
        when (item.loadState.refresh) {
            LoadState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadState.Error -> {
                if (viewModel.query.value.isNotEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = stringResource(R.string.something_went_wrong))
                    }
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.padding(dimen_4)) {
                    items(
                        count = item.itemCount,
                        key = item.itemKey(),
                        contentType = item.itemContentType(),
                    ) { index ->
                        item[index]?.let {
                            PlanetTile(
                                planetInfo = it,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                navigateToDetail = navigateToDetail,
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Preview function for the NasaNavGraph composable.
 * It provides a preview of the UI layout in the Android Studio preview window.
 */
@Composable
@Preview(showBackground = true)
fun PlanetTilePreview() {
    NasaJetpackTheme {
        Surface {
            PlanetTile(
                planetInfo = PlanetInfo(
                    "nasaId",
                    "12:12332:13:Z",
                    "Art of Moon",
                    "test description\ntest line 2\nline 3 \nline 4",
                    "test",
                ),
                modifier = Modifier.fillMaxWidth(),
                navigateToDetail = { arg -> arg },
            )
        }
    }
}
