package ext.aks4125.nasajetpack.presentation.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import ext.aks4125.core.ui.components.shimmerBrush
import ext.aks4125.core.ui.theme.NasaJetpackTheme
import ext.aks4125.nasajetpack.data.network.PlanetInfo
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_150
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_16
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_20
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_4
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_8

/**

Composable function that represents a single item in the search results list.
@param planetInfo The PlanetInfo object representing the planet information.
@param modifier Modifier for styling and positioning.
@param navigateToDetail Callback function for navigating to the detail screen.
 */
@Composable
fun PlanetTile(planetInfo: PlanetInfo, modifier: Modifier, navigateToDetail: (String) -> Unit) {
    val showShimmer = remember { mutableStateOf(true) }
    Card(
        shape = RoundedCornerShape(dimen_8),
        modifier = modifier
            .padding(start = dimen_8, bottom = dimen_8, end = dimen_8)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimen_4,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Row(
            modifier = modifier
                .clickable(onClick = { navigateToDetail(planetInfo.nasaId) })
                .semantics {
                    onClick(
                        label = "Navigate to Detail Screen",
                        action = null,
                    )
                },
        ) {
            Spacer(modifier = Modifier.height(dimen_8))
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimen_20))
                    .background(
                        shimmerBrush(
                            targetValue = 1300f,
                            showShimmer = showShimmer.value,
                        ),
                    )
                    .width(dimen_150)
                    .fillMaxWidth()
                    .height(dimen_150),
                model = planetInfo.imageUrl.orEmpty(),
                contentDescription = null,
                onSuccess = { showShimmer.value = false },
                contentScale = ContentScale.FillBounds,
            )

            Text(
                modifier = modifier
                    .padding(dimen_16)
                    .fillMaxWidth(),
                text = planetInfo.title.orEmpty(),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(dimen_4))
        }
    }

    Spacer(modifier = Modifier.height(dimen_8))
}

/**
 * Preview function for the NasaNavGraph composable.
 * It provides a preview of the UI layout in the Android Studio preview window.
 */
@Composable
@Preview(showBackground = true)
fun PlanetPreview() {
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
