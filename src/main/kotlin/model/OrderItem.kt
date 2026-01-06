package com.aassalax.model

import com.aassalax.tax.Taxes
import java.math.BigDecimal

data class OrderItem(val product: Product, val quantity: Int) {
    private val totalPrice: Price
        get() = Price.from(product.price * quantity)

    val totalTax: Price
        get() = Price.of(Taxes.entries
            .fold(BigDecimal.ZERO) { acc, tax ->
                acc + (tax.computeTaxFor(product) * quantity).asMoney()
            }
        )

    val totalTtc: Price
        get() = totalPrice + totalTax

    override fun toString(): String {
        return "$quantity $product"
    }
}
