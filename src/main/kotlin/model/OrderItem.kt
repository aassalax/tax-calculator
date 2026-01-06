package com.aassalax.model

import com.aassalax.tax.Taxes
import java.math.BigDecimal

data class OrderItem(val product: Product, val quantity: Int) {
    private val totalPrice: Price
        get() = Price.of(product.price.asMoney().multiply(quantity.toBigDecimal()))

    val totalTax: Price
        get() = Price.of(Taxes.entries
            .fold(BigDecimal.ZERO) { acc, tax ->
                acc + tax.computeTaxFor(product).asMoney().multiply(quantity.toBigDecimal())
            }
        )

    val totalTtc: Price
        get() = Price.of(totalPrice.asMoney().add(totalTax.asMoney()))

    override fun toString(): String {
        return "$quantity $product"
    }
}
