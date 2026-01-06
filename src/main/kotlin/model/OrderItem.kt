package com.aassalax.model

import com.aassalax.tax.Taxes
import java.math.BigDecimal

data class OrderItem(val product: Product, val quantity: Int) {
    fun tax() : Price = Price.of(Taxes.entries
        .fold(BigDecimal.ZERO) {
            acc, tax -> acc + tax.computeTaxFor(product)
                .asMoney()
                .multiply(BigDecimal(quantity))
        }
    )

    fun ttc() : Price = Price.of(product.price.asMoney()
        .multiply(BigDecimal(quantity))
        .add(tax().asMoney())
    )

    override fun toString(): String {
        return "$quantity $product"
    }
}
