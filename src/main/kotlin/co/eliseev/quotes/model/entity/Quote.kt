package co.eliseev.quotes.model.entity

import java.math.BigDecimal

data class Quote (
    var id: Long? = null,
    val isin: String,
    val bid: BigDecimal? = null,
    val ask: BigDecimal,
    val bidSize: BigDecimal? = null,
    val askSize: BigDecimal? = null
)
