import com.aassalax.model.OrderItem
import com.aassalax.model.Product
import com.aassalax.model.ProductCategory
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

    @Test
    fun `calculate ttc for book`() {
        val book = Product("livre", BigDecimal(12.49), ProductCategory.BOOK)
        val item = OrderItem(book, 2)

        assertThat(item.ttc()).isEqualByComparingTo("24.98")
    }

    @Test
    fun `calculate ttc for other products`() {
        val cd = Product("CD musical", BigDecimal(14.99))
        val item = OrderItem(cd, 1)

        assertThat(item.ttc()).isEqualByComparingTo("16.49")
    }

    @Test
    fun `calculate tax for other products with quantity greater than one`() {
        val cd = Product("CD musical", BigDecimal(14.99))
        val item = OrderItem(cd, 2)

        assertThat(item.tax()).isEqualByComparingTo(BigDecimal.valueOf(3.00))
    }

    @Test
    fun `calculate ttc for other products with quantity greater than one`() {
        val cd = Product("CD musical", BigDecimal(14.99))
        val item = OrderItem(cd, 2)

        assertThat(item.ttc()).isEqualByComparingTo("32.98")
    }

    @Test
    fun `calculate tax for imported and vat exempted product`() {
        val chocolate = Product(
            name = "boîte de chocolats",
            category = ProductCategory.FOOD,
            price = BigDecimal("10.00"),
            imported = true
        )
        val item = OrderItem(chocolate, 1)

        val expectedTax = BigDecimal("0.50")
        assertThat(item.tax()).isEqualTo(expectedTax)
    }

    @Test
    fun `calculate tax for imported vat non-exempt product`() {
        val perfume = Product(
            name = "flacon de parfum",
            category = ProductCategory.OTHER,
            price = BigDecimal("47.50"),
            imported = true
        )
        val item = OrderItem(perfume, 1)
        val expectedTax = BigDecimal("7.15")
        assertThat(item.tax()).isEqualTo(expectedTax)
    }

    @Test
    fun `calculate ttc for imported and vat exempted product`() {
        val chocolate = Product(
            name = "boîte de chocolats",
            category = ProductCategory.FOOD,
            price = BigDecimal("10.00"),
            imported = true
        )
        val item = OrderItem(chocolate, 1)

        val expectedTtc = BigDecimal("10.50")
        assertThat(item.ttc()).isEqualTo(expectedTtc)
    }

    @Test
    fun `calculate ttc for imported vat non-exempt product`() {
        val perfume = Product(
            name = "imported bottle of perfume",
            category = ProductCategory.OTHER,
            price = BigDecimal("47.50"),
            imported = true
        )
        val item = OrderItem(perfume, 1)

        val expectedTtc = BigDecimal("54.65")
        assertThat(item.ttc()).isEqualTo(expectedTtc)
    }
}