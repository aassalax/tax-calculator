import com.aassalax.Order
import com.aassalax.OrderItem
import com.aassalax.Product
import com.aassalax.ProductCategory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderShould {

    @Nested
    inner class ForSingleItem {
        val book = Product("book", BigDecimal(12.49), ProductCategory.BOOK)
        val item = OrderItem(book, 1)

        @Test
        fun `calculate total taxes`() {
            val order = Order(listOf(item))

            assertThat(order.totalTaxesAmount()).isEqualByComparingTo(BigDecimal("0.00"))
        }

        @Test
        fun `calculate total amount`() {
            val order = Order(listOf(item))

            assertThat(order.priceWithAllTaxesIncluded()).isEqualByComparingTo(BigDecimal("12.49"))
        }
    }

    @Nested
    inner class ForMultipleItems {
        val book = Product("book", BigDecimal("12.49"), ProductCategory.BOOK)
        val cd = Product("music CD", BigDecimal("14.99"), ProductCategory.OTHER)
        val chocolate = Product("chocolate bar", BigDecimal("0.85"),ProductCategory.FOOD)

        @Test
        fun `calculate total taxes`() {
            val order = Order(
                listOf(
                    OrderItem(book, 1),
                    OrderItem(cd, 1),
                    OrderItem(chocolate, 1)
                )
            )

            assertThat(order.totalTaxesAmount()).isEqualByComparingTo(BigDecimal("1.50"))
        }

        @Test
        fun `calculate total amount`() {
            val order = Order(
                listOf(
                    OrderItem(book, 1),
                    OrderItem(cd, 1),
                    OrderItem(chocolate, 1)
                )
            )

            assertThat(order.priceWithAllTaxesIncluded()).isEqualByComparingTo(BigDecimal("29.83"))
        }
    }
}