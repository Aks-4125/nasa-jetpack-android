package ext.aks4125.nasajetpack.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import ext.aks4125.nasajetpack.util.Dimens


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

        Spacer(modifier = Modifier.height(Dimens.dimen_4))

        Card(
            modifier = Modifier.padding(Dimens.dimen_12),
            shape = RoundedCornerShape(Dimens.dimen_10),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
           // TODO ListItem(item = items, viewModel = viewModel, navController = navController)
        }
        Spacer(modifier = Modifier.height(Dimens.dimen_10))
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
                .testTag("search")
                .semantics { this.contentDescription = "Search planets..." },
            query = viewModel.query.value,
            onQueryChange = {
                viewModel.query.value = it
            },
            onSearch = {
                viewModel.invalidateDataSource()
                active = false
                viewModel.setQuery(it)
                //focusManager.clearFocus()
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = { Text(text = "Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            content = {})
    }

}






