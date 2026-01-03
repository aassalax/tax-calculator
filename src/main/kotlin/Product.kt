package com.aassalax

class Product(
    val name: String,
    val price: Double,
    val category: ProductCategory = ProductCategory.OTHER,
) {
    fun isVatExempted() : Boolean {
        return category == ProductCategory.BOOK || category == ProductCategory.FOOD
    }

}

enum class ProductCategory {
    BOOK,
    FOOD,
    MEDICAL,
    OTHER
}