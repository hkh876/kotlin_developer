package com.example.batteryinformation.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.batteryinformation.R

data class HomeUiState(
    val batteryPlugStatus: BatteryPlugStatus = BatteryPlugStatus.NOT_PLUGGED,
    val batteryPercent: String = "0.0 %"
)

enum class BatteryPlugStatus(
    @StringRes val plugLabel: Int,
    @DrawableRes val plugImage: Int,
) {
    NOT_PLUGGED(R.string.not_plugged, -1),
    USB_PLUGGED(R.string.usb_plugged, R.drawable.usb),
    AC_PLUGGED(R.string.ac_plugged, R.drawable.ac)
}
