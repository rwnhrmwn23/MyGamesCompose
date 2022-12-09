package com.onedev.mygamescompose.ui.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.onedev.mygamescompose.ui.theme.MyGamesComposeTheme
import com.onedev.mygamescompose.utils.UIState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    homeViewModel.users.collectAsState().value.let { response ->
        when (response) {
            is UIState.Loading -> {
                homeViewModel.users()
            }
            is UIState.Success -> {
                LazyColumn {
                    val data = response.data.data
                    if (data?.isNotEmpty() == true) {
                        items(data) { item ->
                            UserItem(
                                name = item.first_name + " " + item.last_name,
                                photo = item.avatar.toString()
                            )
                        }
                    }
                }
            }
            is UIState.Error -> {

            }
            else -> { }
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