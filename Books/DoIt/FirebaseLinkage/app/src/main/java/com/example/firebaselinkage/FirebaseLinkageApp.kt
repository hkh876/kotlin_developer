package com.example.firebaselinkage

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.firebaselinkage.ui.navigation.AppNavHost

@Composable
fun FirebaseLinkageApp() {
    AppNavHost(navController = rememberNavController())
}

@Composable
fun FirebaseLinkageTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
) {
    if (canNavigateBack) {
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = { Text(text = title) }
        )
    }
}