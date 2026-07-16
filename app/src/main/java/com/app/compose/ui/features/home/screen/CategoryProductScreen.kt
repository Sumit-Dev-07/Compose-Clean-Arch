package com.app.compose.ui.features.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.compose.ui.theme.PrimaryColor
import com.app.compose.ui.viewmodel.CategoryProductViewModel
import com.app.compose.util.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryProductScreen(
    categoryName: String,
    onBackClick: () -> Unit,
    viewModel: CategoryProductViewModel = hiltViewModel()
) {
    val uiState by viewModel.productState.collectAsStateWithLifecycle()
    Column {
        TopAppBar(
            modifier = Modifier.statusBarsPadding(),
            title = {
                Text(text = categoryName)
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = PrimaryColor,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    ProductSkeleton()
                }

                is UiState.Success -> {
                    ProductList(
                        products = (uiState as UiState.Success).data.products,
                        onLoadMore = {} // No pagination for category products as per requirements for now
                    )
                }

                is UiState.Error -> {
                    Text(
                        text = (uiState as UiState.Error).message,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> Unit
            }
        }
    }
}
