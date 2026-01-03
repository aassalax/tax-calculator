package com.aassalax

import java.math.BigDecimal
import java.math.RoundingMode

class Order(val items: List<OrderItem>) {
    fun totalTaxesAmount() : BigDecimal = items
        .sumOf { it.tax() }
        .setScale(2, RoundingMode.HALF_UP)

    fun priceWithAllTaxesIncluded() : BigDecimal = items
        .sumOf { it.ttc() }
        .setScale(2, RoundingMode.HALF_UP)
}
