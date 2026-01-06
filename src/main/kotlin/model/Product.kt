package com.aassalax.model

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