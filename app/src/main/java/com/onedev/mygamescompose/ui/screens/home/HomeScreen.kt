package com.onedev.mygamescompose.ui.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.ui.theme.MyGamesComposeTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    homeViewModel.users.collectAsState(initial = StateEvent.Loading()).value.let { response ->
        when (response) {
            is StateEvent.Loading -> {
                CircularProgressIndicator()
                homeViewModel.users()
            }
            is StateEvent.Success -> {
                LazyColumn {
                    val data = response.data
                    if (data.isNotEmpty()) {
                        items(data) { item ->
                            UserItem(
                                name = item.first_name + " " + item.last_name,
                                photo = item.avatar.toString()
                            )
                        }
                    }
                }
            }
            is StateEvent.Error -> {

            }
        }
    }
}

@Composable
fun UserItem(
    name: String,
    photo: String
) {
    Row {
        AsyncImage(
            model = photo,
            contentDescription = null,
        )
        Text(text = name)
    }
}

@Composable
@Preview(showBackground = true)
fun UserItemPreview() {
    MyGamesComposeTheme {
        UserItem(
            name = "Irwan Hermawan",
            photo = "https://reqres.in/img/faces/1-image.jpg"
        )
    }
}