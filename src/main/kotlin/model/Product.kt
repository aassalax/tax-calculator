package com.aassalax.model

import java.math.BigDecimal


class Product(
    val name: String,
    val price: Price,
    val category: ProductCategory = ProductCategory.OTHER,
    val imported: Boolean = false
) {
    fun isVatExempted() : Boolean = category.vatExempted

    override fun toString(): String {
        return "$name $price"
    }

}

enum class ProductCategory(val vatExempted: Boolean) {
    BOOK(true),
    FOOD(true),
    MEDICAL(true),
    OTHER(false)
}

@JvmInline
value class Price(val amount: BigDecimal){
    init {
        require(amount >= BigDecimal.ZERO) { "Price must be positive" }
    }

    override fun toString(): String {
        return "$amount"
    }
}