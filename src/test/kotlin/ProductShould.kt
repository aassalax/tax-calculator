import com.aassalax.model.Product
import com.aassalax.model.ProductCategory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ProductShould {

    @Test
    fun `be vat exempted if category is book`() {
        val product = Product("livre", BigDecimal(12.49), ProductCategory.BOOK)

        assertThat(product.isVatExempted()).isTrue
    }

    @Test
    fun `be vat exempted if category is food`() {
        val product = Product("barre de chocolat", BigDecimal(0.85), ProductCategory.FOOD)

        assertThat(product.isVatExempted()).isTrue
    }

    @Test
    fun `be vat exempted if category is other`() {
        val product = Product("CD musical", BigDecimal(14.99), ProductCategory.OTHER)

        assertThat(product.isVatExempted()).isFalse
    }

    @Test
    fun `be vat exempted if category is mediacal`() {
        val product = Product("boîte de pilules contre la migraine", BigDecimal(12.49), ProductCategory.MEDICAL)

        assertThat(product.isVatExempted()).isTrue
    }
}