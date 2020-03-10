package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Quote
import org.springframework.stereotype.Service
import java.math.BigDecimal

interface ElvlCalculator {
    fun calculateElvl(quote: Quote, elvl: BigDecimal?): BigDecimal
}

@Service
class ElvlCalculatorImpl : ElvlCalculator {

    override fun calculateElvl(quote: Quote, elvl: BigDecimal?): BigDecimal {
        val bid = quote.bid
        val ask = quote.ask
        return when {
            elvl == null -> bid ?: ask
            bid == null -> if (ask < elvl) ask else elvl
            bid > elvl -> bid
            ask < elvl -> ask
            else -> elvl
        }
    }

}
