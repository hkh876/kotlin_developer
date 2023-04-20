package com.example.firebaselinkage.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebaselinkage.ui.screens.*

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
                navigateToSignUp = {
                    navController.navigate(SignUpDestination.route)
                },
                navigateToLogOut = {
                    navController.navigate(LogOutDestination.route)
                }
            )
        }

        composable(route = SignUpDestination.route) {
            SignUpScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateToLogOut = {
                    navController.navigate(LogOutDestination.route)
                }
            )
        }

        composable(route = LogOutDestination.route) {
            LogOutScreen(
                navigateToHome = {
                    navController.popBackStack(
                        route = HomeDestination.route,
                        inclusive = false
                    )
                }
            )
        }
    }
}