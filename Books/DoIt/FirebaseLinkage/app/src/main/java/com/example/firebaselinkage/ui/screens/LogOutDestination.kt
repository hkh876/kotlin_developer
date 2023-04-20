package com.example.firebaselinkage.ui.screens

import com.example.firebaselinkage.R
import com.example.firebaselinkage.data.CommonRes.LOGOUT_ROUTE
import com.example.firebaselinkage.ui.navigation.NavigationDestination

object LogOutDestination : NavigationDestination {
    override val route: String = LOGOUT_ROUTE
    override val titleRes: Int = R.string.app_name
}