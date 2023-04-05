package com.example.screenwithjetpackex.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.screenwithjetpackex.R
import com.example.screenwithjetpackex.data.NavigationItem
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val collapsingScaffoldState = rememberCollapsingToolbarScaffoldState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Drawer
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = { HomeDrawerContent() },
        content = {
            // Collapsing toolbar scaffold
            CollapsingToolbarScaffold(
                modifier = Modifier,
                state = collapsingScaffoldState,
                scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
                toolbar = {
                    val fontSize = (18 + (30 - 12) * collapsingScaffoldState.toolbarState.progress).sp

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(color = MaterialTheme.colors.primary)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.baseball),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alpha = if (fontSize.value == 18f) 0f else 1f,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White,
                        fontSize = fontSize,
                        modifier = Modifier
                            .padding(16.dp)
                            .road(
                                whenCollapsed = Alignment.Center,
                                whenExpanded = Alignment.BottomStart
                            )
                    )
                },
            ) {
                LazyColumn() {
                    items(count = 100) {
                        HomeItem(index = it + 1)
                    }
                }
            }

            // Extended floating action button
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = 16.dp,
                        end = 16.dp
                    ),
                contentAlignment = Alignment.BottomEnd
            ) {
                ExtendedFloatingActionButton(
                    text = { Text(text = stringResource(id = R.string.extended_floating_action_button_text)) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null
                        )
                    },
                    onClick = { /*TODO*/ },
                )
            }
        }
    )
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
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Drawer top
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Red),
            contentAlignment = Alignment.BottomStart,
        ) {
            Text(
                text = stringResource(id = R.string.drawer_content_title),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(
                    start = 30.dp,
                    bottom = 30.dp
                )
            )
        }
        HomeDrawerOther()
    }
}

@Composable
fun HomeDrawerOther(modifier: Modifier = Modifier) {
    val itemList = listOf(
        NavigationItem(
            Icons.Filled.Share,
            1
        ),
        NavigationItem(
            ImageVector.vectorResource(id = R.drawable.baseline_question_mark_24),
            2
        ),
        NavigationItem(
            Icons.Filled.Search,
            3
        ),
        NavigationItem(
            Icons.Outlined.AddCircle,
            4
        ),
    )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(start = 30.dp)
    ) {
        itemList.forEach {
            navigationItem -> HomeDrawerNavigationItem(navigationItem = navigationItem)
        }
    }
}

@Composable
fun HomeDrawerNavigationItem(
    modifier: Modifier = Modifier,
    navigationItem: NavigationItem,
) {
    Row(
        modifier = modifier.padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = navigationItem.icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = stringResource(id = R.string.navigation_item_text, navigationItem.itemIndex))
    }
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

@Preview
@Composable
fun PreviewHomeDrawerNavigationItem() {
    HomeDrawerNavigationItem(
        navigationItem = NavigationItem(
            Icons.Filled.Share,
            1
        ),
    )
}