package com.onedev.mygamescompose.ui.screens.detail

import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.onedev.mygamescompose.R
import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.GameDetails
import com.onedev.mygamescompose.ui.components.ErrorView
import com.onedev.mygamescompose.ui.components.LoadingView
import com.onedev.mygamescompose.ui.theme.MyGamesComposeTheme
import com.onedev.mygamescompose.utils.Dummy.gameDetails

@Composable
fun DetailScreen(
    id: Int,
    navigateBack: () -> Unit,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    detailViewModel.games.collectAsState(initial = StateEvent.Loading()).value.let { response ->
        when (response) {
            is StateEvent.Loading -> {
                LoadingView()
                detailViewModel.detailsGame(id)
            }
            is StateEvent.Success -> {
                DetailGames(response.data, navigateBack)
            }
            is StateEvent.Error -> {
                ErrorView(response.exception)
            }
        }
    }
}

@Composable
fun DetailGames(
    data: GameDetails,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .padding(16.dp)
                .clickable { navigateBack() }
        )
        Row(modifier = modifier.padding(8.dp)) {
            Box {
                AsyncImage(
                    model = data.photo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(150.dp)
                        .height(175.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = data.name.toString(),
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp
                    ),
                )
                Html(data.description.toString())
                Spacer(modifier = modifier.padding(8.dp))
                Text(
                    text = stringResource(R.string.release),
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = data.released.toString(),
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }
    }
}

@Composable
fun Html(text: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
        }
    })
}

@Composable
@Preview(showBackground = true)
fun DetailGamesPreview() {
    MyGamesComposeTheme {
        DetailGames(
            gameDetails,
            navigateBack = {}
        )
    }
}