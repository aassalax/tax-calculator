import com.aassalax.Product
import com.aassalax.ProductCategory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductShould {

    @Test
    fun `be vat exempted if category is book`() {
        val product = Product("livre", 12.49, ProductCategory.BOOK)

        assertThat(product.isVatExempted()).isTrue
    }

    @Test
    fun `be vat exempted if category is food`() {
        val product = Product("barre de chocolat", 0.85, ProductCategory.FOOD)

        assertThat(product.isVatExempted()).isTrue
    }

    @Test
    fun `be vat exempted if category is other`() {
        val product = Product("CD musical", 14.99, ProductCategory.OTHER)

        assertThat(product.isVatExempted()).isFalse
    }
}