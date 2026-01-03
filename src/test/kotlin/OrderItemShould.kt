import com.aassalax.OrderItem
import com.aassalax.Product
import com.aassalax.ProductCategory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderItemShould {

    @Test
    fun `calculate zero tax for book`() {
        val book = Product("livre", 12.49, ProductCategory.BOOK)
        val item = OrderItem(book, 1)

        assertThat(item.tax()).isEqualTo(0.0)
    }

    @Test
    fun `calculate zero tax for food`() {
        val chocolat = Product("barre de chocolat", 0.85, ProductCategory.FOOD )
        val item = OrderItem(chocolat, 1)

        assertThat(item.tax()).isEqualByComparingTo(0.0)
    }
}