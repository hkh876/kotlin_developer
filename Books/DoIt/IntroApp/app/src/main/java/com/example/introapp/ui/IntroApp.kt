package com.example.introapp.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.example.introapp.ui.screens.HomeScreen

@Composable
fun IntroApp(windowSize: WindowWidthSizeClass) {
    HomeScreen(windowSize)
}