package com.example.batteryinformation

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.batteryinformation.ui.screens.HomeScreen
import com.example.batteryinformation.ui.screens.sendBroadcast

@Composable
fun BatteryInformationApp() {
    val context = LocalContext.current

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        isGranted -> if (!isGranted) {
            Toast.makeText(
                context,
                context.getString(R.string.permission_deny_text),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            sendBroadcast(context)
        }
    }

    HomeScreen(context, permissionLauncher)
}