package com.example.firebaselinkage.ui.screens

import com.example.firebaselinkage.R
import com.example.firebaselinkage.data.CommonRes.HOME_ROUTE
import com.example.firebaselinkage.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route: String = HOME_ROUTE
    override val titleRes: Int = R.string.app_name
}