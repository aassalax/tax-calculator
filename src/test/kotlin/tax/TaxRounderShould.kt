package tax

import com.aassalax.tax.TaxRounder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class TaxRounderShould {
    @ParameterizedTest(name = "rounding {0} should be {1}")
    @CsvSource(
        "0.99, 1.00",
        "1.00, 1.00",
        "1.01, 1.05",
        "1.02, 1.05"
    )
    fun `round tax correctly according to rules`(input: String, expected: String) {
        val rounded = TaxRounder.round(BigDecimal(input))
        assertThat(rounded).isEqualByComparingTo(BigDecimal(expected))
    }
}