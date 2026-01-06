package com.aassalax.model

data class Order(val items: List<OrderItem>) {
    val totalTaxes: Price
        get() = Price.of(items.sumOf { it.totalTax.asMoney() })

    val totalPriceIncludingTaxes: Price
        get() = Price.of(items.sumOf { it.totalTtc.asMoney() })

    override fun toString(): String = items.joinToString("\n")
}
