package acceptance

import com.aassalax.Invoice
import com.aassalax.Order
import com.aassalax.OrderItem
import com.aassalax.Product
import com.aassalax.ProductCategory
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

class InvoiceScenario : ScenarioTest<GivenOrder, WhenInvoiceIsGenerated, ThenInvoiceIsPrinted>() {

    @Test
    fun `applies vat on taxable products only`() {
        val livre = Product("livre", 12.49, ProductCategory.BOOK)
        val cd = Product("CD musical", 14.99)
        val chocolat = Product("barre de chocolat", 0.85, ProductCategory.FOOD)

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

