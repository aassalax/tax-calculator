package com.aassalax.model

import com.aassalax.tax.Taxes
import java.math.BigDecimal
import java.math.RoundingMode

class OrderItem(val product: Product, val quantity: Int) {
    fun tax() : BigDecimal = Taxes.entries
        .fold(BigDecimal.ZERO) {
            acc, tax -> acc + tax.computeTaxFor(product).multiply(BigDecimal(quantity))
        }

    fun ttc() : BigDecimal {
        return product.price
            .multiply(BigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP)
            .add(tax())
    }
}
