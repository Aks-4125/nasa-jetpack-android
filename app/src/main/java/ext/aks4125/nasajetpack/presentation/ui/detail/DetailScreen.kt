package ext.aks4125.nasajetpack.presentation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ext.aks4125.nasajetpack.R
import ext.aks4125.nasajetpack.data.local.PlanetEntity
import ext.aks4125.nasajetpack.presentation.ui.components.LabelTextMedium
import ext.aks4125.nasajetpack.presentation.theme.NasaJetpackTheme
import ext.aks4125.nasajetpack.presentation.navigation.Dimens
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_10
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_4
import ext.aks4125.nasajetpack.presentation.navigation.Dimens.dimen_8

@Composable
fun DetailScreen() {
    val viewModel = hiltViewModel<DetailViewModel>()
    val uiState = viewModel.uiState
    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.statusBarsPadding()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                PlanetDetail(item = uiState.item, formatDate = { arg -> viewModel.formatDate(arg) })
            }
        }
    }
}

@Composable
fun PlanetDetail(item: PlanetEntity?, formatDate: (String) -> String) {
    Card(
        shape = RoundedCornerShape(Dimens.dimen_8),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimen_8)
            .fillMaxHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.dimen_4,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // -------------------  Image ---------------------
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(Dimens.dimen_20))
                        .padding(top = dimen_10)
                        .width(Dimens.dimen_150)
                        .height(Dimens.dimen_150),
                    model = item?.imageUrl.orEmpty(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.BottomCenter
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimen_8),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                // -------------------  Title ---------------------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    LabelTextMedium(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.label_title)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = Dimens.dimen_16)
                            .weight(3f),
                        text = item?.title.orEmpty(),
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimen_4),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(Dimens.dimen_4))

                // -------------------  Date ---------------------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    LabelTextMedium(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.label_date)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = Dimens.dimen_16)
                            .weight(3f),
                        text = formatDate(item?.dateCreated.orEmpty()),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimen_4),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(Dimens.dimen_4))

                // -------------------  Description ---------------------
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    LabelTextMedium(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.label_description)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = Dimens.dimen_16)
                            .weight(3f),
                        text = item?.description.orEmpty(),
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimen_4),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(Dimens.dimen_4))

            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun PlanetPreview() {
    NasaJetpackTheme {
        Surface {
            PlanetDetail(
                item = PlanetEntity(
                    "nasaId",
                    "12:12332:13:Z",
                    "Art of Moon",
                    "test description\ntest line 2\nline 3 \nline 4",
                    "test"
                ),
                formatDate = { arg -> arg }
            )
        }
    }
}
