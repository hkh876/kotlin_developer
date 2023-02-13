package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
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
fun LemonadeWithImage(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {
    var step by remember {
        mutableStateOf(1)
    }
    var tapCount by remember {
        mutableStateOf(1)
    }
    val descriptions = when(step) {
        1 -> stringResource(R.string.step_1_description_text)
        2 -> stringResource(R.string.step_2_description_text)
        3 -> stringResource(R.string.step_3_description_text)
        else -> stringResource(R.string.step_4_description_text)
    }
    val images = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val contentDescriptions = when(step) {
        1 -> stringResource(R.string.step_1_image_description_text)
        2 -> stringResource(R.string.step_2_image_description_text)
        3 -> stringResource(R.string.step_3_image_description_text)
        else -> stringResource(R.string.step_4_image_description_text)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = descriptions,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = images),
            contentDescription = contentDescriptions,
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(red = 105, green = 205, blue = 216),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
                .clickable {
                    if (step == 1) {
                        tapCount = (2..4).random()
                        step += 1
                    } else if (step == 2 && tapCount != 0) {
                        tapCount -= 1
                        if (tapCount == 0) {
                            step += 1
                        }
                    } else {
                        if (step == 4) {
                            step = 1
                        } else {
                            step += 1
                        }
                    }
                }
        )
    }
}

@Composable
fun LemonadeApp() {
    LemonadeWithImage()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}