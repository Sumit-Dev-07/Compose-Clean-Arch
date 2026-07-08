package com.app.compose.ui.nav

sealed class AppScreen(val route: String) {
    object Main : AppScreen("main")
}
