package com.app.compose.ui.nav

sealed class AppScreen(val route: String) {
    object Splash : AppScreen("splash")
    object Login : AppScreen("login")
    object Home : AppScreen("home")
}
