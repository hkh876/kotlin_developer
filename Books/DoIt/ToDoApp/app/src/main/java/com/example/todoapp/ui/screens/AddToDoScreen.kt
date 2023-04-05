package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.ToDoTopAppBar
import com.example.todoapp.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun AddToDoScreen(
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: AddToDoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ToDoTopAppBar(
                title = stringResource(id = AddToDoDestination.titleRes),
                canNavigateBack = true,
                canSave = true,
                navigateUp = onNavigateUp,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.saveToDo()
                        navigateBack()
                    }
                },
            )
        },
    ) {
        innerPaddingValue -> AddToDoInputForm(
            uiState = viewModel.addToDoUiState,
            onValueChange = viewModel::updateUiState,
            modifier = Modifier.padding(innerPaddingValue)
        )
    }
}

@Composable
fun AddToDoInputForm(
    modifier: Modifier = Modifier,
    uiState: AddToDoUiState,
    onValueChange: (AddToDoUiState) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.add_todo_input_title),
            fontSize = 15.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = modifier.height(16.dp))
        TextField(
            value = uiState.title,
            onValueChange = { onValueChange(uiState.copy(title = it)) },
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewAddToDoInputForm() {
    AddToDoInputForm(
        uiState = AddToDoUiState()
    )
}