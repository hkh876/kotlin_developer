package com.example.screenwithjetpack.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.screenwithjetpack.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null 
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        drawerContent = { HomeDrawerContent() },
        scaffoldState = scaffoldState,
    ) {
        innerPadding ->
        HomeItemDecorationImage()
        LazyColumn(contentPadding = innerPadding) {
            items(count = 100) {
                HomeItem(index = it + 1)
            }
        }
    }
}

@Composable
fun HomeItemDecorationImage(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.kbo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier.size(150.dp)
        )
    }
}

@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    index: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = stringResource(id = R.string.item_text, index))
    }
}

@Composable
fun HomeDrawerContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.drawer_content_text))
    }
}

@Preview
@Composable
fun PreviewHomeItemDecorationImage() {
    HomeItemDecorationImage()
}

@Preview
@Composable
fun PreviewHomeItem() {
    HomeItem(index = 1)
}

@Preview
@Composable
fun PreviewHomeDrawerContent() {
    HomeDrawerContent()
}