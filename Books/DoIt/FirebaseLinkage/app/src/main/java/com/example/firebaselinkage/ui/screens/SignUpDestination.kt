package com.example.firebaselinkage.ui.screens

import com.example.firebaselinkage.R
import com.example.firebaselinkage.data.CommonRes.SIGN_UP_ROUTE
import com.example.firebaselinkage.ui.navigation.NavigationDestination

object SignUpDestination : NavigationDestination {
    override val route: String = SIGN_UP_ROUTE
    override val titleRes: Int = R.string.signup_title
}