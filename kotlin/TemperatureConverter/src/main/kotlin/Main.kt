fun main() {
    val celsiusToFahrenheitFunc: (Double) -> Double = {
        it * 9 / 5 + 32
    }
    val kelvinToCelsiusFunc: (Double) -> Double = {
        it - 273.15
    }
    val fahrenheitToKelvinFunc: (Double) -> Double = {
        (it - 32) * 5 / 9 + 273.15
    }

    printFinalTemperature(27.0, "Celsius", "Fahrenheit", celsiusToFahrenheitFunc)
    printFinalTemperature(350.0, "Kelvin", "Celsius", kelvinToCelsiusFunc)
    printFinalTemperature(10.0, "Fahrenheit", "Kelvin", fahrenheitToKelvinFunc)
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

