package co.eliseev.quotes.model.dto

import co.eliseev.quotes.configuration.ISIN_SIZE
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
data class QuoteDto( // TODO add validation ask greater than bid
    val id: Long? = null,
    val isin: @NotBlank @Size(min = ISIN_SIZE, max = ISIN_SIZE) String,
    val bid: BigDecimal? = null,
    val ask: BigDecimal,
    val bidSize: BigDecimal? = null,
    val askSize: BigDecimal? = null
)
