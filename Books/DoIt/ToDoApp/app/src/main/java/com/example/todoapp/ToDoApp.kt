package com.example.todoapp

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.navigation.AppNavHost

@Composable
fun ToDoApp(navController: NavHostController = rememberNavController()) {
    AppNavHost(navController = navController)
}

@Composable
fun ToDoTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean = false,
    canSave: Boolean = false,
    navigateUp: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    if (canNavigateBack) {
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = {
                if (canSave) {
                    TextButton(onClick = onSaveClick) {
                        Text(
                            text = stringResource(id = R.string.save_button_text),
                            color = Color.White
                        )
                    }
                }
            }
        )
    } else {
        TopAppBar(
            title = { Text(text = title)}
        )
    }
}

@Preview
@Composable
fun PreviewCanBackAndSaveTopAppBar() {
    ToDoTopAppBar(
        title = "Add To Do",
        canNavigateBack = true,
        canSave = true
    )
}