package com.aassalax.tax

import com.aassalax.model.Price
import com.aassalax.model.Product
import java.math.BigDecimal

enum class Taxes(val rate: BigDecimal, val isApplicableFor: (Product) -> Boolean) {
    VAT(rate = BigDecimal("0.10"), isApplicableFor = { it.isVatExempted().not() }),
    IMPORTED(rate = BigDecimal("0.05"), isApplicableFor = { it.imported });

    fun computeTaxFor(product: Product): Price =
        if (isApplicableFor(product))
            Price.of(TaxRounder.round(product.price.amount * rate))
        else
            Price.of(BigDecimal.ZERO)
}