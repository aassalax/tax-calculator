package com.aassalax.model

data class Order(val items: List<OrderItem>) {
    fun totalTaxesAmount() : Price = Price.of(items
        .sumOf { it.tax().asMoney() })

    fun priceWithAllTaxesIncluded() : Price = Price.of(items
        .sumOf { it.ttc().asMoney() })

    override fun toString(): String = items.joinToString("\n")
}
