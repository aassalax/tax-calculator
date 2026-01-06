package com.aassalax

import com.aassalax.model.Invoice
import com.aassalax.model.Order
import com.aassalax.model.OrderItem
import com.aassalax.model.Price
import com.aassalax.model.Product
import com.aassalax.model.ProductCategory
import java.math.BigDecimal

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val order1 = Order(
        listOf(
            OrderItem(
                Product(
                    name = "livre",
                    category = ProductCategory.BOOK,
                    price = Price.of(BigDecimal("12.49"))
                ),
                quantity = 1
            ),
            OrderItem(
                Product(
                    name = "CD musical",
                    category = ProductCategory.OTHER,
                    price = Price.of(BigDecimal("14.99"))
                ),
                quantity = 1
            ),
            OrderItem(
                Product(
                    name = "barre de chocolat",
                    category = ProductCategory.FOOD,
                    price = Price.of(BigDecimal("0.85"))
                ),
                quantity = 1
            )
        )
    )

    val order2 = Order(
        listOf(
            OrderItem(
                Product(
                    name = "boîte de chocolats importée",
                    category = ProductCategory.FOOD,
                    price = Price.of(BigDecimal("10.00")),
                    imported = true
                ),
                quantity = 1
            ),
            OrderItem(
                Product(
                    name = "flacon de parfum importé",
                    category = ProductCategory.OTHER,
                    price = Price.of(BigDecimal("47.50")),
                    imported = true
                ),
                quantity = 1
            )
        )
    )

    val order3 = Order(
        listOf(
            OrderItem(
                Product(
                    name = "flacon de parfum importé",
                    category = ProductCategory.OTHER,
                    price = Price.of(BigDecimal("27.99")),
                    imported = true
                ),
                quantity = 1
            ),
            OrderItem(
                Product(
                    name = "flacon de parfum",
                    category = ProductCategory.OTHER,
                    price = Price.of(BigDecimal("18.99"))
                ),
                quantity = 1
            ),
            OrderItem(
                Product(
                    name = "boîte de pilules contre la migraine",
                    category = ProductCategory.MEDICAL,
                    price = Price.of(BigDecimal("9.75"))
                ),
                quantity = 1
            ),
            OrderItem(
                Product(
                    name = "boîte de chocolats importés",
                    category = ProductCategory.FOOD,
                    price = Price.of(BigDecimal("11.25")),
                    imported = true
                ),
                quantity = 1
            )
        )
    )

    val orders = listOf(order1, order2, order3)

    orders.forEachIndexed { i, order ->
        val index = i + 1
        println("INPUT $index :")
        println(order)
        println("OUTPUT $index :")
        println(Invoice(order).print())
        println()
    }
}