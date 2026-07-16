package com.app.compose.ui.features.home

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.app.compose.ui.features.home.screen.CategoryProductScreen
import com.app.compose.ui.features.home.screen.DashboardScreen
import com.app.compose.ui.features.home.screen.ProductScreen
import com.app.compose.ui.features.home.screen.ProfileScreen
import com.app.compose.ui.features.home.screen.appBarTitle
import com.app.compose.ui.features.home.screen.selectedIcon
import com.app.compose.ui.theme.Grey70
import com.app.compose.ui.theme.OnestBold
import com.app.compose.ui.theme.OnestMedium
import com.app.compose.ui.theme.OnestSemiBold
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
        topBar = {
            if (currentRoute != "category_products/{category}/{categoryName}") {
                TopAppBar(
                    title = { Text(selectedIndex.intValue.appBarTitle(), fontFamily = OnestSemiBold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PrimaryColor,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            }
        },
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
                    fontFamily = OnestMedium,
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
            modifier = Modifier.padding(paddingValues),
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(300)) },
            popExitTransition = { fadeOut(animationSpec = tween(300)) }
        ) {
            composable("home") {
                DashboardScreen(
                    onCategoryClick = { category ->
                        navController.navigate("category_products/${category.slug}/${category.name}")
                    }
                )
            }
            composable("product") { ProductScreen() }
            composable("profile") { ProfileScreen() }

            composable(
                route = "category_products/{category}/{categoryName}",
                arguments = listOf(
                    navArgument("category") { type = NavType.StringType },
                    navArgument("categoryName") { type = NavType.StringType }
                ),
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(400)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(400)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(400)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(400)
                    )
                }
            ) { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: ""
                val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                CategoryProductScreen(
                    categoryName = categoryName,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
