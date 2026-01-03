package com.aassalax.model

import com.aassalax.tax.IMPORT_TAX_RATE
import com.aassalax.tax.TaxRounder
import com.aassalax.tax.VAT_RATE
import java.math.BigDecimal
import java.math.RoundingMode

class OrderItem(val product: Product, val quantity: Int) {
    fun tax() : BigDecimal {
        val vat = if(product.category == ProductCategory.BOOK || product.category == ProductCategory.FOOD) BigDecimal(0.0)
        else TaxRounder.round(product.price * VAT_RATE).multiply(BigDecimal(quantity))

        val importTax = if(product.imported) TaxRounder
            .round(product.price * IMPORT_TAX_RATE)
            .multiply(BigDecimal(quantity))
        else BigDecimal.ZERO

        return vat.add(importTax)
    }

    fun ttc() : BigDecimal {
        return product.price
            .multiply(BigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP)
            .add(tax())
    }
}
