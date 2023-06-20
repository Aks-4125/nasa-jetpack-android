package ext.aks4125.nasajetpack.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import ext.aks4125.nasajetpack.R
import ext.aks4125.nasajetpack.data.PlanetInfo
import ext.aks4125.nasajetpack.ui.components.ShimmerBrush
import ext.aks4125.nasajetpack.util.Dimens.dimen_10
import ext.aks4125.nasajetpack.util.Dimens.dimen_150
import ext.aks4125.nasajetpack.util.Dimens.dimen_16
import ext.aks4125.nasajetpack.util.Dimens.dimen_20
import ext.aks4125.nasajetpack.util.Dimens.dimen_4
import ext.aks4125.nasajetpack.util.Dimens.dimen_8


@Composable
internal fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel
) {
    val items = viewModel.itemPager.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CustomSearchBar(viewModel)
        // lazy load items
        ListItem(item = items, viewModel = viewModel)
    }

}

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
                    style = MaterialTheme.typography.labelMedium,
                    text = "Search",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { viewModel.setQuery("") }) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null
                    )
                }
            },
            content = {})
        Spacer(modifier = Modifier.height(dimen_4))
    }
}


@Composable
fun ListItem(
    item: LazyPagingItems<PlanetInfo>,
    viewModel: SearchViewModel,
) {

    if (viewModel.query.value.isNotEmpty())
        when (item.loadState.refresh) {
            LoadState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadState.Error -> {
                if (viewModel.query.value.isNotEmpty())
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(R.string.something_went_wrong))
                    }
            }

            else -> {
                LazyColumn(modifier = Modifier.padding(dimen_4)) {
                    itemsIndexed(item) { index, item ->
                        item?.let {
                            PlanetTile(
                                planetInfo = item,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
}

@Composable
fun PlanetTile(planetInfo: PlanetInfo, modifier: Modifier) {
    val showShimmer = remember { mutableStateOf(true) }


    Card(
        shape = RoundedCornerShape(dimen_8),
        modifier = modifier
            .padding(start = dimen_10, top = dimen_4, bottom = dimen_10)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimen_10,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Row(modifier = Modifier.clickable(onClick = { })) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimen_20))
                    .background(
                        ShimmerBrush(
                            targetValue = 1300f, showShimmer = showShimmer.value
                        )
                    )
                    .width(dimen_150)
                    .fillMaxWidth()
                    .height(dimen_150),
                model = planetInfo.imageUrl.orEmpty(),
                contentDescription = null,
                onSuccess = { showShimmer.value = false },
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = modifier
                    .padding(dimen_16)
                    .fillMaxWidth(),
                text = planetInfo.title.orEmpty(),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(dimen_4))
        }
    }

    Spacer(modifier = Modifier.height(dimen_8))
}




