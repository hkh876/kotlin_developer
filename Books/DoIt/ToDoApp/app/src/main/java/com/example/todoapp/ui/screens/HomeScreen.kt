package com.example.todoapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.ToDoTopAppBar
import com.example.todoapp.data.ToDoEntity
import com.example.todoapp.ui.AppViewModelProvider

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToAddToDo: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.todoListStream.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            ToDoTopAppBar(
                title = stringResource(id = HomeDestination.titleRes),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.action_button_text)) },
                onClick = navigateToAddToDo,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            )
        }
    ) {
        HomeToDoList(toDoList = homeUiState)
    }
}

@Composable
fun HomeToDoList(
    modifier: Modifier = Modifier,
    toDoList: List<ToDoEntity>,
) {
    LazyColumn() {
        items(items = toDoList) {
            toDo -> HomeToDoListItem(toDo = toDo)
        }
    }
}

@Composable
fun HomeToDoListItem(
    modifier: Modifier = Modifier,
    toDo: ToDoEntity,
) {
    Column() {
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.todo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier.size(24.dp)
            )
            Spacer(modifier = modifier.width(24.dp))
            Text(
                text = toDo.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Divider(

        )
    }
}

@Preview
@Composable
fun PreviewHomeToDoListItem() {
    HomeToDoListItem(toDo = ToDoEntity(1, "Study English"))
}