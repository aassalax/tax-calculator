import com.aassalax.model.Price
import com.aassalax.model.Product
import com.aassalax.model.ProductCategory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class ProductShould {

    @Test
    fun `be vat exempted if category is book`() {
        val product = Product("livre", Price(BigDecimal(12.49)), ProductCategory.BOOK)

        assertThat(product.isVatExempted()).isTrue
    }

    @Test
    fun `be vat exempted if category is food`() {
        val product = Product("barre de chocolat", Price(BigDecimal(0.85)), ProductCategory.FOOD)

        assertThat(product.isVatExempted()).isTrue
    }

    @Test
    fun `be vat exempted if category is other`() {
        val product = Product("CD musical", Price(BigDecimal(14.99)), ProductCategory.OTHER)

        assertThat(product.isVatExempted()).isFalse
    }

    @Test
    fun `be vat exempted if category is mediacal`() {
        val product = Product("boîte de pilules contre la migraine", Price(BigDecimal(12.49)), ProductCategory.MEDICAL)

        assertThat(product.isVatExempted()).isTrue
    }

    @Test
    fun `should throw exception for negative price`() {
        assertThrows<IllegalArgumentException> {
            Price(BigDecimal("-1.00"))
        }
    }
}