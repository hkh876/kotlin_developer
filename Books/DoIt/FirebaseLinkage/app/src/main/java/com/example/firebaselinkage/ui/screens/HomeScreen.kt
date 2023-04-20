package com.example.firebaselinkage.ui.screens

import android.view.KeyEvent.ACTION_DOWN
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebaselinkage.FirebaseLinkageTopAppBar
import com.example.firebaselinkage.R
import com.example.firebaselinkage.ui.AppViewModelProvider

@Composable
fun HomeScreen(
    navigateToSignUp: () -> Unit = {},
    navigateToLogOut: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
    val uiState = viewModel.homeUiState

    Scaffold(
        topBar = {
            FirebaseLinkageTopAppBar(
                title = stringResource(id = HomeDestination.titleRes)
            )
        }
    ) {
        HomeSignUpOrLogin(
            modifier = Modifier.padding(it),
            uiState = uiState,
            onNavigateToSignUp = navigateToSignUp,
            onEmailChange = viewModel::updateUiState,
            onPasswordChange = viewModel::updateUiState,
            onLoginClick = {
                viewModel.login(context)
            },
        )
    }

    if (uiState.successLogin) {
        viewModel.clear()
        navigateToLogOut()
    }
}

@Composable
fun HomeSignUpOrLogin(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onNavigateToSignUp: () -> Unit = {},
    onEmailChange: (HomeUiState) -> Unit = {},
    onPasswordChange: (HomeUiState) -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.login_or_signup),
            modifier = modifier.padding(top = 20.dp)
        )
        Button(
            onClick = onNavigateToSignUp,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = stringResource(id = R.string.signup_button_text))
        }
        HomeInputForm(
            uiState = uiState,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onLoginClick = onLoginClick,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeInputForm(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEmailChange: (HomeUiState) -> Unit = {},
    onPasswordChange: (HomeUiState) -> Unit = {},
    onLoginClick: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Column() {
        TextField(
            value = uiState.email,
            onValueChange = { onEmailChange(uiState.copy(email = it)) },
            placeholder = {
                Text(text = stringResource(id = R.string.email_placeholder_text))
            },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .onPreviewKeyEvent {
                    if (it.key == Key.Tab && it.nativeKeyEvent.action == ACTION_DOWN) {
                        focusManager.moveFocus(FocusDirection.Down)
                        true
                    } else {
                        false
                    }
                }
        )
        TextField(
            value = uiState.password,
            onValueChange = { onPasswordChange(uiState.copy(password = it)) },
            placeholder = {
                Text(text = stringResource(id = R.string.password_placeholder_text))
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .onPreviewKeyEvent {
                    if (it.key == Key.Tab && it.nativeKeyEvent.action == ACTION_DOWN) {
                        focusManager.clearFocus()
                        true
                    } else {
                        false
                    }
                }
        )
        Button(
            onClick = onLoginClick,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = stringResource(id = R.string.login_button_text))
        }
    }
}

@Preview
@Composable
fun PreviewHomeSignUpOrLogin() {
    HomeSignUpOrLogin(uiState = HomeUiState())
}
