package com.aassalax.tax

import java.math.BigDecimal
import java.math.RoundingMode

object TaxRounder {
    private val scale = BigDecimal("0.05")

    fun round(amount: Double): BigDecimal = BigDecimal.valueOf(amount)
        .divide(scale, 0, RoundingMode.UP)
        .multiply(scale)
}