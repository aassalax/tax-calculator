package com.aassalax.model

import java.math.BigDecimal


class Product(
    val name: String,
    val price: Price,
    val category: ProductCategory = ProductCategory.OTHER,
    val imported: Boolean = false
) {
    fun isVatExempted() : Boolean {
        return category == ProductCategory.BOOK ||
                category == ProductCategory.FOOD ||
                category == ProductCategory.MEDICAL
    }

    override fun toString(): String {
        return "$name $price"
    }

}

enum class ProductCategory {
    BOOK,
    FOOD,
    MEDICAL,
    OTHER
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