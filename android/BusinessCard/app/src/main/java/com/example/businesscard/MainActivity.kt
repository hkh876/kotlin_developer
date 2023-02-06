package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF003A4A)
                ) {
                    BusinessCardApp()
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
fun CardWithNameTitle(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val image = painterResource(id = R.drawable.android_logo)

        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .width(180.dp)
                .height(100.dp)
        )
        Text(
            text = stringResource(R.string.name_text),
            fontSize = 46.sp,
            fontWeight = FontWeight.Light,
            color = Color.White,
            modifier = Modifier
                .padding(top = 10.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = stringResource(R.string.job_text),
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3ddc84),
            modifier = Modifier
                .padding(top = 4.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun InfoItem(icon: ImageVector, contents: String) {
    Row(
        modifier = Modifier
            .padding(start = 60.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF3ddc84)
        )
        Spacer(Modifier.width(24.dp))
        Text(
            text = contents,
            color = Color.White
        )
    }
}

@Composable
fun InfoLine() {
    Divider(
        color = Color(0xFF215663),
        thickness = 2.dp
    )
}

@Composable
fun CardWithIconInfo(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        InfoLine()
        InfoItem(Icons.Filled.Phone, stringResource(R.string.phone_number_text))
        InfoLine()
        InfoItem(Icons.Filled.Share, stringResource(R.string.share_text))
        InfoLine()
        InfoItem(Icons.Filled.Email, stringResource(R.string.email_text))
    }
}

@Composable
fun BusinessCardApp() {
    Column(

    ) {
        CardWithNameTitle(Modifier.weight(4f))
        CardWithIconInfo(Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF003a4a)
        ) {
            BusinessCardApp()
        }
    }
}