package com.aassalax.model

import com.aassalax.tax.Taxes
import java.math.BigDecimal

data class OrderItem(val product: Product, val quantity: Int) {
    private val totalPrice: Price
        get() = Price.from(product.price * quantity)

    val totalTax: Price
        get() = Price.of(tax())

    val totalTtc: Price
        get() = totalPrice + totalTax

    private fun tax(): BigDecimal = Taxes.entries.fold(BigDecimal.ZERO) { acc, tax ->
        acc + (tax.computeTaxFor(product) * quantity).asMoney()
    }

    override fun toString(): String {
        return "$quantity $product"
    }
}
