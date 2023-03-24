package com.example.calculatorapp.libs.exprk.internal

import java.math.BigDecimal

abstract class Function {

    abstract fun call(arguments: List<BigDecimal>): BigDecimal

}