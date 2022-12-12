package com.onedev.mygamescompose.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.onedev.mygamescompose.R
import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.Games
import com.onedev.mygamescompose.ui.components.ErrorView
import com.onedev.mygamescompose.ui.components.GameItem
import com.onedev.mygamescompose.ui.components.LoadingView

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.popular),
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ),
        )
        GamesScreen()
    }
}

@Composable
fun GamesScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    homeViewModel.games.collectAsState(initial = StateEvent.Loading()).value.let { response ->
        when (response) {
            is StateEvent.Loading -> {
                LoadingView()
                homeViewModel.games(20)
            }
            is StateEvent.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    val games = response.data
                    if (games.isNotEmpty()) {
                        items(games) { item ->
                            GameItem(
                                photo = item.photo.toString(),
                                name = item.name.toString(),
                                date = item.released.toString(),
                            )
                        }
                    }
                }
            }
            is StateEvent.Error -> {
                ErrorView(response.exception)
            }
        }
    }
}