package com.aassalax

class OrderItem(val product: Product, val quantity: Int) {
    fun tax() : Double {
        return if(product.category == ProductCategory.BOOK) 0.0
        else 0.10
    }
}
