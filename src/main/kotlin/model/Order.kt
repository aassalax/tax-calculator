package com.aassalax.model

data class Order(val items: List<OrderItem>) {
    fun totalTaxesAmount() : Price = Price(items
        .sumOf { it.tax().asMoney() })

    fun priceWithAllTaxesIncluded() : Price = Price(items
        .sumOf { it.ttc().asMoney() })

    override fun toString(): String = items.joinToString("\n")
}
