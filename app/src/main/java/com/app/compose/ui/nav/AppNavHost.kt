package com.app.compose.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.compose.ui.features.main.LoginScreen
import com.app.compose.ui.features.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.Splash.route
    ) {
        composable(AppScreen.Splash.route) {
            SplashScreen(onNavigateToMain = {
                navController.navigate(AppScreen.Main.route) {
                    popUpTo(AppScreen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(AppScreen.Main.route) {
            LoginScreen()
        }
    }
}
