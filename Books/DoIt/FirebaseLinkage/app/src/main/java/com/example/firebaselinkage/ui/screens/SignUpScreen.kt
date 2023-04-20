package com.example.firebaselinkage.ui.screens

import android.view.KeyEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebaselinkage.FirebaseLinkageTopAppBar
import com.example.firebaselinkage.R
import com.example.firebaselinkage.ui.AppViewModelProvider

@Composable
fun SignUpScreen(
    onNavigateUp: () -> Unit,
    navigateToLogOut: () -> Unit,
    viewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current
    val uiState = viewModel.signUpUiState

    Scaffold(
        topBar = {
            FirebaseLinkageTopAppBar(
                title = stringResource(id = SignUpDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) {
        SignUpInputForm(
            modifier = Modifier.padding(it),
            onSignUpClick = {
                email, password -> viewModel.signUp(email, password, context)
            }
        )
    }

    if (uiState.isSuccess) {
        viewModel.updateUiState(uiState.copy(isSuccess = false))
        navigateToLogOut()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpInputForm(
    modifier: Modifier = Modifier,
    onSignUpClick: (String, String) -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.padding(20.dp)
    ) {
        Text(text = stringResource(id = R.string.login_or_signup))
        TextField(
            value = email.value,
            onValueChange = { email.value = it},
            placeholder = {
                Text(text = stringResource(id = R.string.email_placeholder_text))
            },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .onPreviewKeyEvent {
                    if (it.key == Key.Tab && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                        focusManager.moveFocus(FocusDirection.Down)
                        true
                    } else {
                        false
                    }
                }
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = {
                Text(text = stringResource(id = R.string.password_placeholder_text))
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .onPreviewKeyEvent {
                    if (it.key == Key.Tab && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                        focusManager.clearFocus()
                        true
                    } else {
                        false
                    }
                }
        )
        Button(
            onClick = { onSignUpClick(email.value, password.value) },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(text = stringResource(id = R.string.signup_button_text))
        }
    }
}
