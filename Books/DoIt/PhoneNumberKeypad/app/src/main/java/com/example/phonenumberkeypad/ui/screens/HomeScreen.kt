package com.example.phonenumberkeypad.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonenumberkeypad.R

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddContact()
        Text(
            text = "02-120",
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Keypad()
        VideoCallBackIcons()
    }
}

@Composable
fun AddContact(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(
                top = 20.dp,
                bottom = 100.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = null,
            tint = Color(0xFF00FF00)
        )
        Text(
            text = "연락처 추가",
            color = Color(0xFF00FF00),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Keypad(modifier: Modifier = Modifier) {
    val itemList = listOf(
        "1", "2", "3",
        "4", "5", "6",
        "7", "8", "9",
        "*", "0", "#"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 36.dp)
    ) {
        items(itemList) {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(
                    start = 40.dp,
                    end = 40.dp,
                    top = 10.dp,
                    bottom = 10.dp
                )
            )
        }
    }
}

@Composable
fun VideoCallBackIcons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.video),
            contentDescription = null,
            modifier = modifier.size(50.dp)
        )
        Spacer(modifier = modifier.width(30.dp))
        Image(
            painter = painterResource(id = R.drawable.call),
            contentDescription = null,
            modifier = modifier.size(50.dp)
        )
        Spacer(modifier = modifier.width(30.dp))
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = modifier.size(50.dp)
        )
    }
}

@Preview
@Composable
fun PreviewAddContact() {
    AddContact()
}

@Preview
@Composable
fun PreviewKeypad() {
    Keypad()
}

@Preview
@Composable
fun PreviewVideoCallBackIcons() {
    VideoCallBackIcons()
}
