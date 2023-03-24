package com.example.calculatorapp

import android.app.Application
import com.example.calculatorapp.data.AppContainer
import com.example.calculatorapp.data.AppDataContainer
import com.example.calculatorapp.data.InputItems

class CalculatorApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        InputItems.initialize(this)
    }
}