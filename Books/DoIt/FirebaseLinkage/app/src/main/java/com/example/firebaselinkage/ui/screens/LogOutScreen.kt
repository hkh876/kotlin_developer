package com.example.firebaselinkage.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebaselinkage.FirebaseLinkageTopAppBar
import com.example.firebaselinkage.R
import com.example.firebaselinkage.ui.AppViewModelProvider

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LogOutScreen(
    navigateToHome: () -> Unit,
    viewModel: LogOutViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    BackHandler() {
        // Nothing.
    }

    Scaffold(
        topBar = {
            FirebaseLinkageTopAppBar(
                title = stringResource(id = LogOutDestination.titleRes)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.welcome_text, viewModel.email),
                modifier = Modifier.padding(top = 20.dp)
            )
            Button(
                onClick = {
                    viewModel.logout()
                    navigateToHome()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(text = stringResource(id = R.string.logout_button_text))
            }
        }
    }
}