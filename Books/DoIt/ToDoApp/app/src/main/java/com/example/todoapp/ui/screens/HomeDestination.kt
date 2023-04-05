package com.example.todoapp.ui.screens

import com.example.todoapp.R
import com.example.todoapp.data.CommonRes.HOME_ROUTE
import com.example.todoapp.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = HOME_ROUTE
    override val titleRes = R.string.app_name
}
