package com.app.compose.ui.features.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.compose.R

fun Int.selectedIcon(itemIndex: Int): Int {
    return when (itemIndex) {
        0 -> if (this == 0) R.drawable.active_home else R.drawable.home
        1 -> if (this == 1) R.drawable.active_product else R.drawable.product
        2 -> if (this == 2) R.drawable.active_profile else R.drawable.profile
        else -> R.drawable.home
    }
}

@Composable
fun DashboardScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Dashboard Screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Profile Screen")
    }
}