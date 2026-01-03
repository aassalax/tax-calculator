package com.aassalax

class Product(
    val name: String,
    val price: Double,
    val category: ProductCategory = ProductCategory.OTHER,
) {

}

enum class ProductCategory {
    BOOK,
    FOOD,
    MEDICAL,
    OTHER
}