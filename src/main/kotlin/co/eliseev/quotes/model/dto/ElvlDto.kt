package co.eliseev.quotes.model.dto

import co.eliseev.quotes.configuration.ISIN_SIZE
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

data class ElvlDto(
    val isin: @NotBlank @Size(min = ISIN_SIZE, max = ISIN_SIZE) String,
    val value: @PositiveOrZero BigDecimal
)
