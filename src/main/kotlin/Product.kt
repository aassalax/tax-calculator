package com.aassalax

class Product(
    name: String,
    price: Double,
    category: ProductCategory = ProductCategory.OTHER,
) {

}

enum class ProductCategory {
    BOOK,
    FOOD,
    MEDICAL,
    OTHER
}