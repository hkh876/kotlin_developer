package com.example.amphibiansapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibiansapp.network.Amphibian
import com.example.amphibiansapp.ui.screens.AmphibianCard
import com.example.amphibiansapp.ui.screens.AmphibiansScreen
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansAppTheme {
                AmphibiansApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AmphibiansAppTheme {
        AmphibiansApp()
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansCardPreview() {
    val amphibians = listOf(
        Amphibian(
            "title",
            "type",
            "description",
            ""
        )
    )

    AmphibiansAppTheme {
        AmphibiansScreen(amphibians = amphibians)
    }
}