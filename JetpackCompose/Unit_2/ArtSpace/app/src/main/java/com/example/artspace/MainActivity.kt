package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ArtWithImage(
    imageID: Int,
    imageDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 4.dp,
                    color = Color.DarkGray
                )
                .padding(32.dp)
        ) {
            Image(
                painter = painterResource(id = imageID),
                contentDescription = imageDescription,
            )
        }
    }
}

@Composable
fun ArtWithTitle(
    title: String,
    author: String,
    postDate: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                )
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp
            )
            Row() {
                Text(
                    text = author,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = postDate,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ArtWithButtons(
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.width(160.dp),
            onClick = onPrevClick
        ) {
            Text("Previous")
        }
        Button(
            modifier = Modifier.width(160.dp),
            onClick = onNextClick
        ) {
            Text("Next")
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var contentIndex by remember { mutableStateOf(1) }
    val imageID = when(contentIndex) {
        1 -> R.drawable.charlesdeluvio_mv9hjneuhr4_unsplash
        2 -> R.drawable.kevin_mueller_aeng4ya41p8_unsplash
        3 -> R.drawable.kar_ming_moo_q_3wmguwgyg_unsplash
        4 -> R.drawable.laura_college_k_na5gcmh38_unsplash
        else -> R.drawable.wexor_tmg_l_2p8fapoa8_unsplash
    }
    val imageDescription = when(contentIndex) {
        1 -> stringResource(id = R.string.content_1_image_description_text)
        2 -> stringResource(id = R.string.content_2_image_description_text)
        3 -> stringResource(id = R.string.content_3_image_description_text)
        4 -> stringResource(id = R.string.content_4_image_description_text)
        else -> stringResource(id = R.string.content_5_image_description_text)
    }
    val title = when(contentIndex) {
        1 -> stringResource(id = R.string.content_1_title_text)
        2 -> stringResource(id = R.string.content_2_title_text)
        3 -> stringResource(id = R.string.content_3_title_text)
        4 -> stringResource(id = R.string.content_4_title_text)
        else -> stringResource(id = R.string.content_5_title_text)
    }
    val author = when(contentIndex) {
        1 -> stringResource(id = R.string.content_1_author_text)
        2 -> stringResource(id = R.string.content_2_author_text)
        3 -> stringResource(id = R.string.content_3_author_text)
        4 -> stringResource(id = R.string.content_4_author_text)
        else -> stringResource(id = R.string.content_5_author_text)
    }
    val postDate = when(contentIndex) {
        1 -> stringResource(id = R.string.content_date_text, "2018")
        2 -> stringResource(id = R.string.content_date_text, "2020")
        3 -> stringResource(id = R.string.content_date_text, "2019")
        4 -> stringResource(id = R.string.content_date_text, "2017")
        else -> stringResource(id = R.string.content_date_text, "2015")
    }

    val previousClickEvent = {
        if(contentIndex == 1) {
            contentIndex = 5
        } else {
            contentIndex -= 1
        }
    }

    val nextClickEvent = {
        if(contentIndex == 5) {
            contentIndex = 1
        } else {
            contentIndex += 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        ArtWithImage(
            imageID,
            imageDescription,
            Modifier.weight(7f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ArtWithTitle(
            title,
            author,
            postDate,
            Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ArtWithButtons(
            previousClickEvent,
            nextClickEvent,
            Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}