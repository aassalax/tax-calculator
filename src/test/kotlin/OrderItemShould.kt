import com.aassalax.OrderItem
import com.aassalax.Product
import com.aassalax.ProductCategory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderItemShould {

    @Test
    fun `calculate zero tax for book`() {
        val book = Product("livre", BigDecimal(12.49), ProductCategory.BOOK)
        val item = OrderItem(book, 1)

        assertThat(item.tax()).isEqualByComparingTo(BigDecimal.ZERO)
    }

    @Test
    fun `calculate zero tax for food`() {
        val chocolat = Product("barre de chocolat", BigDecimal(0.85), ProductCategory.FOOD )
        val item = OrderItem(chocolat, 1)

        assertThat(item.tax()).isEqualByComparingTo(BigDecimal.ZERO)
    }

    @Test
    fun `calculate 10 percent tax for other products`() {
        val cd = Product("CD musical", BigDecimal(14.99))
        val item = OrderItem(cd, 1)

        assertThat(item.tax()).isEqualByComparingTo(BigDecimal.valueOf(1.50))
    }
}