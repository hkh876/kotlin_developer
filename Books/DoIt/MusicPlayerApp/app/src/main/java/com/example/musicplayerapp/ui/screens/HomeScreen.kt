package com.example.musicplayerapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.musicplayerapp.R

@Composable
fun HomeScreen(
    onMessengerPlayClick: () -> Unit,
    onMessengerPlayStopClick: () -> Unit,
    onAIDLPlayClick: () -> Unit,
    onAIDLPlayStopClick: () -> Unit,
) {
    Column(modifier = Modifier.background(Color.Black)) {
        HomePlayStopAction(
            title = stringResource(id = R.string.title_for_messenger_play),
            onPlayClick = onMessengerPlayClick,
            onPlayStopClick = onMessengerPlayStopClick
        )
        Spacer(modifier = Modifier.weight(1f))
        HomePlayStopAction(
            title = stringResource(id = R.string.title_for_aidl_play),
            onPlayClick = onAIDLPlayClick,
            onPlayStopClick = onAIDLPlayStopClick
        )
    }
}

@Composable
fun HomePlayStopAction(
    title: String,
    onPlayClick: () -> Unit,
    onPlayStopClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPlayClick) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = null,
                tint = Color.White
            )
        }
        Text(
            text = title,
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onPlayStopClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_stop_24),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}