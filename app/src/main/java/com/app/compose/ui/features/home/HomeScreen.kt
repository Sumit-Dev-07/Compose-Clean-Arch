package com.app.compose.ui.features.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.app.compose.ui.features.home.screen.DashboardScreen
import com.app.compose.ui.features.home.screen.ProductScreen
import com.app.compose.ui.features.home.screen.ProfileScreen
import com.app.compose.ui.features.home.screen.selectedIcon
import com.app.compose.ui.theme.Grey70
import com.app.compose.ui.theme.PrimaryColor
import com.app.compose.ui.theme.WhiteColor

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    val items = listOf(
        SmoothAnimationBottomBarScreens(
            route = "home",
            name = "Home",
            icon = selectedIndex.intValue.selectedIcon(0)
        ),
        SmoothAnimationBottomBarScreens(
            route = "product",
            name = "Product",
            icon = selectedIndex.intValue.selectedIcon(1)
        ),
        SmoothAnimationBottomBarScreens(
            route = "profile",
            name = "Profile",
            icon = selectedIndex.intValue.selectedIcon(2)
        ),
    )

    Scaffold(
        bottomBar = {
            SmoothAnimationBottomBar(
                navController = navController,
                bottomNavigationItems = items,
                initialIndex = selectedIndex,
                bottomBarProperties = BottomBarProperties(
                    backgroundColor = WhiteColor,
                    indicatorColor = PrimaryColor.copy(alpha = 0.2F),
                    iconTintActiveColor = PrimaryColor,
                    iconTintColor = Grey70,
                    textActiveColor = PrimaryColor,
                    cornerRadius = 18.dp,
                    fontSize = 16.sp,
                ),
                onSelectItem = {
                    selectedIndex.intValue = items.indexOf(it)
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { DashboardScreen() }
            composable("product") { ProductScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
