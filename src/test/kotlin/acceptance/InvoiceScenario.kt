package acceptance

import com.aassalax.model.Invoice
import com.aassalax.model.Order
import com.aassalax.model.OrderItem
import com.aassalax.model.Price
import com.aassalax.model.Product
import com.aassalax.model.ProductCategory
import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ExpectedScenarioState
import com.tngtech.jgiven.annotation.ProvidedScenarioState
import com.tngtech.jgiven.junit5.ScenarioTest
import io.toolisticon.testing.jgiven.GIVEN
import io.toolisticon.testing.jgiven.JGivenKotlinStage
import io.toolisticon.testing.jgiven.THEN
import io.toolisticon.testing.jgiven.WHEN
import io.toolisticon.testing.jgiven.step
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class InvoiceScenario : ScenarioTest<GivenOrder, WhenInvoiceIsGenerated, ThenInvoiceIsPrinted>() {

    @Test
    fun `applies vat on taxable products only`() {
        val livre = Product("livre", Price.of(BigDecimal(12.49)), ProductCategory.BOOK)
        val cd = Product("CD musical", Price.of(BigDecimal(14.99)))
        val chocolat = Product("barre de chocolat", Price.of(BigDecimal(0.85)), ProductCategory.FOOD)

        GIVEN.`an order with items`(
            OrderItem(livre, 1),
            OrderItem(cd, 1),
            OrderItem(chocolat, 1),
        )
        WHEN.`the invoice is generated`()
        THEN.`the printed invoice is`("""
        1 livre : 12.49
        1 CD musical : 16.49
        1 barre de chocolat : 0.85
        Montant des taxes: 1.50
        Total: 29.83
        """.trimIndent()
        )
    }

    @Test
    fun `apply additional import tax`() {
        val importedChocolate = Product(
            name = "boîte de chocolats importée",
            category = ProductCategory.FOOD,
            price = Price.of(BigDecimal("10.00")),
            imported = true
        )
        val importedPerfume = Product(
            name = "flacon de parfum importé",
            category = ProductCategory.OTHER,
            price = Price.of(BigDecimal("47.50")),
            imported = true
        )

        GIVEN.`an order with items`(
            OrderItem(importedChocolate, 1),
            OrderItem(importedPerfume, 1)
        )
        WHEN.`the invoice is generated`()
        THEN.`the printed invoice is`("""
            1 boîte de chocolats importée : 10.50
            1 flacon de parfum importé : 54.65
            Montant des taxes: 7.65
            Total: 65.15
        """.trimIndent())
    }

    @Test
    fun `all kinds of products`() {
        val importedPerfume = Product("flacon de parfum importé", price = Price.of(BigDecimal("27.99")), imported = true)
        val perfume = Product("flacon de parfum", price = Price.of(BigDecimal("18.99")))
        val migrainePills = Product("boîte de pilules contre la migraine", category = ProductCategory.MEDICAL, price = Price.of(BigDecimal("9.75")))
        val importedChocolates = Product("boîte de chocolats importés", category = ProductCategory.FOOD, price = Price.of(BigDecimal("11.25")), imported = true)

        GIVEN.`an order with items`(
            OrderItem(importedPerfume, 1),
            OrderItem(perfume, 1),
            OrderItem(migrainePills, 1),
            OrderItem(importedChocolates, 1),
        )

        WHEN.`the invoice is generated`()

        THEN.`the printed invoice is`("""
        1 flacon de parfum importé : 32.19
        1 flacon de parfum : 20.89
        1 boîte de pilules contre la migraine : 9.75
        1 boîte de chocolats importés : 11.85
        Montant des taxes: 6.70
        Total: 74.68
    """.trimIndent())
    }
}

@JGivenKotlinStage
open class GivenOrder : Stage<GivenOrder>() {

    @ProvidedScenarioState
    lateinit var order : Order

    fun `an order with items`(vararg items : OrderItem) = step {
        this.order = Order(items.toList())
    }
}

@JGivenKotlinStage
open class WhenInvoiceIsGenerated : Stage<WhenInvoiceIsGenerated>() {

    @ExpectedScenarioState
    lateinit var order : Order

    @ProvidedScenarioState
    lateinit var invoice : Invoice

    fun `the invoice is generated`() = step {
        this.invoice = Invoice(order)
    }
}

@JGivenKotlinStage
open class ThenInvoiceIsPrinted : Stage<ThenInvoiceIsPrinted>() {

    @ExpectedScenarioState
    lateinit var invoice : Invoice

    fun `the printed invoice is`(expected : String) = step {
        assertThat(invoice.print()).isEqualTo(expected)
    }
}

