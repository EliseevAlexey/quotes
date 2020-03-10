package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Quote
import java.math.BigDecimal

interface ElvlCalculator {
    fun calculateElvl(quote: Quote, elvl: BigDecimal?): BigDecimal
}
