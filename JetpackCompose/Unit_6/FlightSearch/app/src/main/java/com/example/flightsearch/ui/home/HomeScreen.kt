package com.example.flightsearch.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.FlightFavoriteItem
import com.example.flightsearch.data.FlightSearchItem
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.theme.FlightSearchTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val allItems by viewModel.getAllItems().collectAsState(initial = emptyList())
    val homeUiState = viewModel.homeUiState
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        SearchInputForm(
            uiState = homeUiState,
            onValueChange = {
                query ->
                var searchItemList: List<FlightSearchItem> = listOf()
                if(query.isNotEmpty()) {
                    searchItemList = allItems.filter { item ->
                        val lowerQuery = query.lowercase()
                        item.name.lowercase().contains(lowerQuery)
                                || item.iataCode.lowercase().contains(lowerQuery)
                    }
                }

                viewModel.updateHomeUiState(
                    homeUiState.copy(
                        query = query,
                        selectedItem = null,
                        searchItemList = searchItemList
                    )
                )
            }
        )

        if(homeUiState.selectedItem != null) {
            val allFavorites by viewModel.getFavorites().collectAsState(initial = emptyList())
            var availableItemList:MutableList<HomeFavoriteUiState> = mutableListOf()

            allItems.forEachIndexed() {
                index, item ->
                val departCode = homeUiState.selectedItem.iataCode
                val destinationCode = item.iataCode

                if(departCode != destinationCode) {
                    val isFavorite = allFavorites.any {
                        favoriteItem ->
                        favoriteItem.departureCode == departCode
                                && favoriteItem.destinationCode == destinationCode
                    }

                    availableItemList.add(
                        HomeFavoriteUiState(
                            id = index,
                            departCode = departCode,
                            departName = homeUiState.selectedItem.name,
                            destinationCode = destinationCode,
                            destinationName = item.name,
                            isFavorite = isFavorite,
                        )
                    )
                }
            }

            // Available Flights
            AvailableFlightsBody(
                item =  homeUiState.selectedItem,
                availableItemList = availableItemList,
                onFavoriteClick = {
                    uiState ->

                    if(!uiState.isFavorite) {
                        coroutine.launch {
                            viewModel.insertFavoriteItem(uiState.toItem())
                        }
                    } else {
                        coroutine.launch {
                            viewModel.deleteFavoriteItemByCode(uiState.departCode, uiState.destinationCode)
                        }
                    }

                    viewModel.updateHomeFavoriteUiState(uiState.copy(isFavorite = !uiState.isFavorite))
                }
            )
        }
        else {
            if(homeUiState.query.isEmpty()) {
                val allFavorites by viewModel.getFavorites().collectAsState(initial = emptyList())
                if(allFavorites.isNotEmpty()) {
                    // Favorite Flights
                    FavoriteFlightsBody(
                        favoriteItems = allFavorites,
                        allItems = allItems,
                        onFavoriteClick = {
                            uiState ->
                            coroutine.launch {
                                viewModel.deleteFavoriteItemByCode(
                                    departCode = uiState.departCode,
                                    destinationCode = uiState.destinationCode
                                )
                            }
                        }
                    )
                }
            } else {
                val focusManager = LocalFocusManager.current

                SearchResultsBody(
                    searchItemList = homeUiState.searchItemList,
                    onItemClick = {
                        focusManager.clearFocus()
                        viewModel.updateHomeUiState(homeUiState.copy(selectedItem = it))
                    }
                )
            }
        }
    }
}

@Composable
fun SearchInputForm(
    uiState: HomeUiState,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    TextField(
        value =  if(uiState.selectedItem != null) uiState.selectedItem.iataCode else uiState.query,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun SearchResultsBody(
    searchItemList: List<FlightSearchItem>,
    onItemClick: (FlightSearchItem) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(
            items = searchItemList,
            key = { it.id }
        ) {
            item -> SearchResultItem(
                onItemClick = onItemClick,
                item = item
            )
        }
    }
}

@Composable
fun SearchResultItem(
    onItemClick: (FlightSearchItem) -> Unit,
    item: FlightSearchItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) },
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = item.iataCode,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.name,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun AvailableFlightsBody(
    item: FlightSearchItem,
    availableItemList: MutableList<HomeFavoriteUiState>,
    onFavoriteClick: (HomeFavoriteUiState) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.available_flights_title, item.iataCode),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = availableItemList,
                key = { it.id }
            ) {
                AvailableFlightsItem(
                    uiState = it,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@Composable
fun AvailableFlightsItem(
    uiState: HomeFavoriteUiState,
    onFavoriteClick: (HomeFavoriteUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteIcon =
        if(uiState.isFavorite) painterResource(id = R.drawable.baseline_star_24)
        else painterResource(id = R.drawable.outline_star_outline_24)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier.weight(8f)
        ) {
            Text(
                text = stringResource(id = R.string.available_flights_depart),
                fontSize = 10.sp,
                style = MaterialTheme.typography.subtitle2
            )
            Row() {
                Text(
                    text = uiState.departCode,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = uiState.departName)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.available_flights_arrive),
                fontSize = 10.sp,
                style = MaterialTheme.typography.subtitle2
            )
            Row() {
                Text(
                    text = uiState.destinationCode,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = uiState.destinationName)
            }
        }
        Icon(
            painter = favoriteIcon,
            contentDescription = stringResource(id = R.string.favorite_icon_description),
            modifier = modifier
                .weight(1f)
                .clickable { onFavoriteClick(uiState) }
        )
    }
}

@Composable
fun FavoriteFlightsBody(
    favoriteItems: List<FlightFavoriteItem>,
    allItems: List<FlightSearchItem>,
    onFavoriteClick: (HomeFavoriteUiState) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.favorite_title),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = favoriteItems,
                key = { it.id }
            ) {
                val departItem = allItems.filter {
                    allItem ->
                    allItem.iataCode.contains(it.departureCode)
                }
                val destinationItem = allItems.filter {
                    allItem ->
                    allItem.iataCode.contains(it.destinationCode)
                }

                if(departItem.isNotEmpty() && destinationItem.isNotEmpty()) {
                    val uiState = HomeFavoriteUiState(
                        id = it.id,
                        departCode = it.departureCode,
                        departName = departItem.first().name,
                        destinationCode = it.destinationCode,
                        destinationName = destinationItem.first().name,
                        isFavorite = true
                    )

                    AvailableFlightsItem(
                        uiState = uiState,
                        onFavoriteClick = { onFavoriteClick(uiState) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchInputFormPreview() {
    FlightSearchTheme {
        SearchInputForm(HomeUiState())
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultBodyPreview() {
    FlightSearchTheme {
        SearchResultsBody(
            searchItemList = listOf(
                FlightSearchItem(
                    id = 1,
                    name = "Sheremetyevo - A. S. Pushkin international",
                    iataCode = "SVO",
                    passengers = 100
                ),
                FlightSearchItem(
                    id = 2,
                    name = "Munich International Airport",
                    iataCode = "MUC",
                    passengers = 101
                ),
                FlightSearchItem(
                    id = 3,
                    name = "Athens International Airport",
                    iataCode = "ATH",
                    passengers = 102
                ),
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AvailableFlightsBodyPreview() {
    FlightSearchTheme() {
        AvailableFlightsBody(
            item = FlightSearchItem(
                id = 0,
                name = "Sheremetyevo - A. S. Pushkin international",
                iataCode = "SVO",
                passengers = 100
            ),
            availableItemList = mutableListOf(
                HomeFavoriteUiState(
                    id = 1,
                    departCode = "SVO",
                    departName = "Sheremetyevo - A. S. Pushkin international",
                    destinationCode = "MUC",
                    destinationName = "Munich International Airport"
                ),
                HomeFavoriteUiState(
                    id = 2,
                    departCode = "SVO",
                    departName = "Sheremetyevo - A. S. Pushkin international",
                    destinationCode = "ATH",
                    destinationName = "Athens International Airport"
                ),
            ),
            onFavoriteClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteFlightsBodyPreview() {
    FlightSearchTheme() {
        FavoriteFlightsBody(
            favoriteItems = listOf(
              FlightFavoriteItem(
                  id = 0,
                  departureCode = "SVO",
                  destinationCode = "ATH"
              )
            ),
            allItems = listOf(
                FlightSearchItem(
                    id = 0,
                    name = "Sheremetyevo - A. S. Pushkin international",
                    iataCode = "SVO",
                    passengers = 100
                ),
                FlightSearchItem(
                    id = 1,
                    name = "Munich International Airport",
                    iataCode = "MUC",
                    passengers = 101
                ),
                FlightSearchItem(
                    id = 2,
                    name = "Athens International Airport",
                    iataCode = "ATH",
                    passengers = 102
                ),
            )
        )
    }
}