package com.aassalax.model

import java.math.BigDecimal
import java.math.RoundingMode


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

    fun asMoney(): BigDecimal =
        amount.setScale(2, RoundingMode.HALF_UP)

    override fun toString(): String {
        return "$amount"
    }
}