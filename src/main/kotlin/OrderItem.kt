package com.aassalax

class OrderItem(val product: Product, val quantity: Int) {
    fun tax() : Double {
        return if(product.category == ProductCategory.BOOK || product.category == ProductCategory.FOOD) 0.0
        else product.price * VAT_RATE
    }
}
