package com.aassalax

import java.math.BigDecimal
import java.math.RoundingMode

class Order(val items: List<OrderItem>) {
    fun totalTaxesAmount() : BigDecimal {
        return items.sumOf { it.tax() }
    }

    fun priceWithAllTaxesIncluded() : BigDecimal {
        return items
            .sumOf { it.ttc() }
            .setScale(2, RoundingMode.HALF_UP)
    }
}
