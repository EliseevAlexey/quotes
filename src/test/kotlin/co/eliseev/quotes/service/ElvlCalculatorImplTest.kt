package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Quote
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ElvlCalculatorImplTest {

    private val elvlCalculator = ElvlCalculatorImpl()

    @Test
    fun calculateElvl() {
        val bid = 100.toBigDecimal()
        val ask = 120.toBigDecimal()
        val quote = Quote(
            isin = testIsin,
            bid = bid,
            ask = ask
        )
        val elvl = 100.toBigDecimal()

        assertEquals(elvl, elvlCalculator.calculateElvl(quote, elvl))
    }

    @Test
    fun `calculate when elvl and bid is null then return ask`() {
        val ask = 120.toBigDecimal()
        val quote = Quote(
            isin = testIsin,
            ask = ask
        )

        assertEquals(ask, elvlCalculator.calculateElvl(quote, null))
    }

    @Test
    fun `calculate when elvl is null then return bid`() {
        val ask = 120.toBigDecimal()
        val bid = 100.toBigDecimal()
        val quote = Quote(
            isin = testIsin,
            ask = ask,
            bid = bid
        )

        assertEquals(bid, elvlCalculator.calculateElvl(quote, null))
    }

    @Test
    fun `calculate when bid is null and ask less than elvl then return ask`() {
        val ask = 120.toBigDecimal()
        val elvl = 130.toBigDecimal()
        val quote = Quote(
            isin = testIsin,
            ask = ask
        )

        assertEquals(ask, elvlCalculator.calculateElvl(quote, elvl))
    }

    @Test
    fun `calculate when bid is null and ask greater than elvl then return elvl`() {
        val ask = 120.toBigDecimal()
        val elvl = 110.toBigDecimal()
        val quote = Quote(
            isin = testIsin,
            ask = ask
        )

        assertEquals(elvl, elvlCalculator.calculateElvl(quote, elvl))
    }

    @Test
    fun `calculate when bid greater than elvl then return bid`() {
        val ask = 120.toBigDecimal()
        val bid = 110.toBigDecimal()
        val elvl = 100.toBigDecimal()
        val quote = Quote(
            isin = testIsin,
            bid = bid,
            ask = ask
        )

        assertEquals(bid, elvlCalculator.calculateElvl(quote, elvl))
    }

    @Test
    fun `calculate when ask less than elvl then return ask`() {
        val ask = 120.toBigDecimal()
        val bid = 110.toBigDecimal()
        val elvl = 130.toBigDecimal()
        val quote = Quote(
            isin = testIsin,
            bid = bid,
            ask = ask
        )

        assertEquals(ask, elvlCalculator.calculateElvl(quote, elvl))
    }

    @Test
    fun `calculate when elvl less than ask and greater than bid then return elvl`() {
        val bid = 110.toBigDecimal()
        val elvl = 115.toBigDecimal()
        val ask = 120.toBigDecimal()
        val quote = Quote(
            isin = testIsin,
            bid = bid,
            ask = ask
        )

        assertEquals(elvl, elvlCalculator.calculateElvl(quote, elvl))
    }

    companion object {
        private const val testIsin = "AAAA9999BBBB"
    }

}
