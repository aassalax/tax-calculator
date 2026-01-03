package com.aassalax

import java.math.BigDecimal

class Order(val items: List<OrderItem>) {
    fun totalTaxesAmount() : BigDecimal {
        return items.sumOf { it.tax() }
    }

    fun priceWithAllTaxesIncluded() : BigDecimal {
        TODO("Not yet implemented")
    }
}
