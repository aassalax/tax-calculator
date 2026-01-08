package com.aassalax.model

class Invoice(val order: Order) {
    fun print() : String {
        val lines = order.items.joinToString("\n") { it.asInvoiceLine() }
        val totalTaxes = order.totalTaxes
        val total = order.totalPriceIncludingTaxes

        return buildString {
            appendLine(lines)
            appendLine("Montant des taxes: $totalTaxes")
            append("Total: $total")
        }
    }

    private fun OrderItem.asInvoiceLine() : String {
        return "$quantity ${product.name} : $totalTtc"
    }
}