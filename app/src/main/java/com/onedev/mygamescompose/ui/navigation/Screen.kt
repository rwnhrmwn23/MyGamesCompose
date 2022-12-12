package com.onedev.mygamescompose.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object DetailGames: Screen("home/{id}") {
        fun createRoute(id: Int?) = "home/$id"
    }
}