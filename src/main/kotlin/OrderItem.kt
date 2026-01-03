package com.aassalax

import com.aassalax.tax.TaxRounder
import com.aassalax.tax.VAT_RATE
import java.math.BigDecimal

class OrderItem(val product: Product, val quantity: Int) {
    fun tax() : BigDecimal {
        return if(product.category == ProductCategory.BOOK || product.category == ProductCategory.FOOD) BigDecimal(0.0)
        else TaxRounder.round(product.price * VAT_RATE)
    }
}
