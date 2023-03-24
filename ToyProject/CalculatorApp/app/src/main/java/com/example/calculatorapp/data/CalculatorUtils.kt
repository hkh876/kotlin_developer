package com.example.calculatorapp.data

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import com.example.calculatorapp.R
import com.example.calculatorapp.libs.exprk.Expressions

enum class InputItems(
    @StringRes val labelId: Int
) {
    Zero(R.string.zero),
    One(R.string.one),
    Two(R.string.two),
    Three(R.string.three),
    Four(R.string.four),
    Five(R.string.five),
    Six(R.string.six),
    Seven(R.string.seven),
    Eight(R.string.eight),
    Nine(R.string.nine),
    Plus(R.string.plus),
    Minus(R.string.minus),
    Multiply(R.string.multiply),
    Divide(R.string.divide),
    Point(R.string.point),
    Equal(R.string.equal),
    Clear(R.string.clear),
    Delete(R.string.delete);

    lateinit var label: String

    companion object {
        fun initialize(context: Context) {
            values().forEach {
                value -> value.label = context.getString(value.labelId)
            }
        }
    }
}

class StaticData {
    companion object {
        const val UTILS_TAG = "CalculatorUtils"
        val inputSigns: List<List<InputItems>> = listOf(
            listOf(InputItems.Clear, InputItems.Divide),
            listOf(InputItems.Seven, InputItems.Eight, InputItems.Nine,InputItems.Multiply),
            listOf(InputItems.Four, InputItems.Five, InputItems.Six, InputItems.Minus),
            listOf(InputItems.One, InputItems.Two, InputItems.Three, InputItems.Plus),
            listOf(InputItems.Zero, InputItems.Point, InputItems.Delete, InputItems.Equal)
        )
    }
}

fun performCalculations(fieldValue: String): String {
    try {
        val signs = arrayOf(
            InputItems.Plus.label,
            InputItems.Minus.label,
            InputItems.Multiply.label,
            InputItems.Divide.label
        )

        if (!signs.any { fieldValue.contains(it) }
            || signs.any { fieldValue.endsWith(it) }) {
            return ""
        }

        val eval = Expressions().eval(fieldValue)
        val outputValue = eval.toString()

        Log.d(StaticData.UTILS_TAG, "output value : $outputValue")
        return outputValue

    } catch (exception: Exception) {
        Log.e(StaticData.UTILS_TAG, "Error : $exception")
        return ""
    }
}
