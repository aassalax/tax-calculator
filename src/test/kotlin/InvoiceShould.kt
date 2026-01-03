import com.aassalax.Invoice
import com.aassalax.Order
import com.aassalax.OrderItem
import com.aassalax.Product
import com.aassalax.ProductCategory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class InvoiceShould {

    @Test
    fun `print a single item`() {
        val book = Product("book", BigDecimal("12.49"), ProductCategory.BOOK)
        val order = Order(listOf(OrderItem(book, 1)))
        val invoice = Invoice(order)

        val expected = """
            1 book : 12.49
            Montant des taxes: 0.00
            Total: 12.49
        """.trimIndent()

        assertThat(invoice.print()).isEqualTo(expected)
    }

    @Test
    fun `print multiple items with correct taxes and totals`() {
        val book = Product("book", BigDecimal("12.49"), ProductCategory.BOOK)
        val cd = Product("music CD", BigDecimal("14.99"))
        val chocolate = Product("chocolate bar", BigDecimal("0.85"),ProductCategory.FOOD)

        val order = Order(
            listOf(
                OrderItem(book, 1),
                OrderItem(cd, 1),
                OrderItem(chocolate, 1)
            )
        )

        val invoice = Invoice(order)

        val expected = """
            1 book : 12.49
            1 music CD : 16.49
            1 chocolate bar : 0.85
            Montant des taxes: 1.50
            Total: 29.83
        """.trimIndent()

        assertThat(invoice.print()).isEqualTo(expected)
    }
}