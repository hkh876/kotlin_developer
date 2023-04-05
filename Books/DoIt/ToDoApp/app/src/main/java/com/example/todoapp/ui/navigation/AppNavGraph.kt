package com.example.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.ui.screens.AddToDoDestination
import com.example.todoapp.ui.screens.AddToDoScreen
import com.example.todoapp.ui.screens.HomeDestination
import com.example.todoapp.ui.screens.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToAddToDo = {
                    navController.navigate(AddToDoDestination.route)
                }
            )
        }

        composable(route = AddToDoDestination.route) {
            AddToDoScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}