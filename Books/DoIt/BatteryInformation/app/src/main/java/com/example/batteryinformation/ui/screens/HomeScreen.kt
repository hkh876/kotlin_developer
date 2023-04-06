package com.example.batteryinformation.ui.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.batteryinformation.R
import com.example.batteryinformation.receiver.BatteryReceiver
import com.example.batteryinformation.ui.AppViewModelProvider

@Composable
fun HomeScreen(
    context: Context,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState = viewModel.homeUiState

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BatteryStatus(uiState = homeUiState)
            Button(
                onClick = {
                    registerReceiver(context, viewModel)
                    runReceiver(context, permissionLauncher)
                }
            ) {
                Text(text = stringResource(id = R.string.run_receiver_button_text))
            }
        }
    }
}

@Composable
fun BatteryStatus(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
) {
    val batteryPlugStatus = uiState.batteryPlugStatus
    val isPlugged = batteryPlugStatus != BatteryPlugStatus.NOT_PLUGGED

    if (isPlugged) {
        Image(
            painter = painterResource(id = batteryPlugStatus.plugImage),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(300.dp)
        )
    }
    Text(
        text = stringResource(id = batteryPlugStatus.plugLabel),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.battery_percent_text, uiState.batteryPercent),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

fun registerReceiver(
    context: Context,
    viewModel: HomeViewModel
) {
    var batteryPlugStatus = viewModel.homeUiState.batteryPlugStatus

    context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))!!.apply {
        when (getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
            BatteryManager.BATTERY_STATUS_CHARGING -> {
                batteryPlugStatus = when (getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
                    BatteryManager.BATTERY_PLUGGED_USB -> {
                        // usb plugged
                        BatteryPlugStatus.USB_PLUGGED
                    }
                    BatteryManager.BATTERY_PLUGGED_AC -> {
                        // ac plugged
                        BatteryPlugStatus.AC_PLUGGED
                    }
                    else -> {
                        BatteryPlugStatus.NOT_PLUGGED
                    }
                }
            }
            else -> {
                // not plugged
                batteryPlugStatus = BatteryPlugStatus.NOT_PLUGGED
            }
        }

        val level = getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val batteryPct = level / scale.toFloat() * 100

        Log.d("KH", "battery percent : $batteryPct")
        // set battery percent
        viewModel.updateUiState(
            viewModel.homeUiState.copy(
                batteryPlugStatus = batteryPlugStatus,
                batteryPercent = batteryPct.toString()
            )
        )
    }
}

fun runReceiver(
    context: Context,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
    val permission = Manifest.permission.POST_NOTIFICATIONS

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                permission
        ) == PackageManager.PERMISSION_GRANTED) {
            sendBroadcast(context)
        } else {
            permissionLauncher.launch(permission)
        }
    }
}

fun sendBroadcast(context: Context) {
    val intent = Intent(context, BatteryReceiver::class.java)
    context.sendBroadcast(intent)
}