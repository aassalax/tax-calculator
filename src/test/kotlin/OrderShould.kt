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
    }
}