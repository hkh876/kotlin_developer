package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.model.ItemModel
import com.example.newsapp.ui.AppViewModelProvider

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val homeUiState = viewModel.homeUiState

    Scaffold(
        topBar = {
            TopAppBar() {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(items = homeUiState.news.articles) {
                item -> HomeNewsItem(item = item)
            }
        }
    }
}

@Composable
fun HomeNewsItem(
    modifier: Modifier = Modifier,
    item: ItemModel
) {
    Column(modifier = modifier
        .padding(vertical = 10.dp)
    ) {
        Text(
            text = item.title,
            color = Color(0xFF212121),
            fontSize = 14.sp,
            modifier = modifier.padding(
                start = 20.dp,
                end = 10.dp
            )
        )
        Text(
            text = stringResource(id = R.string.author_and_published, item.author, item.publishedAt),
            color = Color(0xFF727272),
            fontSize = 12.sp,
            modifier = modifier.padding(
                start = 20.dp,
                end = 10.dp,
                top = 3.dp
            )
        )
        Text(
            text = item.description,
            color = Color(0xFF212121),
            fontSize = 14.sp,
            modifier = modifier.padding(
                start = 13.dp,
                top = 15.dp,
                end = 10.dp
            )
        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(item.urlToImage)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    bottom = 5.dp
                )
        )
        Divider(modifier = modifier.padding(top = 3.dp))
    }
}

@Preview
@Composable
fun PreviewHomeNewsItem() {
    HomeNewsItem(
        item = ItemModel(
            id = 1,
            author = "Jake Peterson",
            title = "This Service Will Hang Onto Your Luggage While You Explore a New City",
            description = "Here’s a familiar travel scenario: You just checked out of your hotel, but you have a while before your flight. That’s fine, because you have plenty of time to relax and explore the city before you leave. But just before you step out into the world for a fun …",
            urlToImage = "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/604b77afdcd8c4189586f4b2e8967e8e.png",
            publishedAt = "2023-03-28T18:00:00Z"
        )
    )
}