package com.example.stopwatchapp.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stopwatchapp.AppViewModelProvider
import com.example.stopwatchapp.R

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val homeUiState = viewModel.homeUiState
    val context = LocalContext.current
    var initTime = 0L

    BackHandler() {
        if(System.currentTimeMillis() - initTime > 3000L) {
            Toast.makeText(
                context,
                R.string.exit_toast_text,
                Toast.LENGTH_SHORT
            ).show()
            initTime = System.currentTimeMillis()
        } else {
            (context as Activity).finish()
        }
    }

    Column(modifier = Modifier.fillMaxHeight()) {
        DisplayTime(
            uiState = homeUiState,
            modifier = Modifier.weight(1f)
        )
        ActionButtons(
            uiState = homeUiState,
            onStartClick = viewModel::startTimer,
            onStopClick = viewModel::stopTimer,
            onResetClick = viewModel::resetTimer,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DisplayTime(
    modifier: Modifier = Modifier,
    uiState: HomeUiState
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = uiState.timeValue,
            fontSize = 60.sp
        )
    }
}

@Composable
fun ActionButtons(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onStartClick: () -> Unit = {},
    onStopClick: () -> Unit = {},
    onResetClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 40.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onStartClick,
            shape = RoundedCornerShape(30.dp),
            enabled = uiState.startIsEnable
        ) {
            Text(text = stringResource(id = R.string.start_button))
        }
        Button(
            onClick = onStopClick,
            shape = RoundedCornerShape(30.dp),
            enabled = uiState.stopIsEnabled
        ) {
            Text(text = stringResource(id = R.string.stop_button))
        }
        Button(
            onClick = onResetClick,
            shape = RoundedCornerShape(30.dp),
            enabled = uiState.resetIsEnabled
        ) {
            Text(text = stringResource(id = R.string.reset_button))
        }
    }
}

@Preview
@Composable
fun PreviewDisplayTime() {
    DisplayTime(uiState = HomeUiState())
}

@Preview
@Composable
fun PreviewActionButtons() {
    ActionButtons(uiState = HomeUiState())
}