package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Elvl
import co.eliseev.quotes.model.entity.Quote
import co.eliseev.quotes.repository.QuoteRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class QuoteServiceImplTest {

    private val quoteRepository = mock<QuoteRepository>()
    private val elvlService = mock<ElvlService>()
    private val elvlCalculator = mock<ElvlCalculator>()
    private lateinit var quoteService: QuoteService

    @BeforeEach
    fun reset() {
        reset(quoteRepository, elvlService, elvlCalculator)
        quoteService = QuoteServiceImpl(quoteRepository, elvlService, elvlCalculator)
    }

    @Test
    fun `save new quote and elvl was same then do not call merge`() {
        val elvlValue = 100.2.toBigDecimal()
        val quote = Quote(isin = testIsin, bid = 100.2.toBigDecimal(), ask = 101.9.toBigDecimal())
        val elvl = Elvl(isin = testIsin, value = elvlValue)
        whenever(elvlService.getByIsin(testIsin)).thenReturn(elvl)
        whenever(elvlCalculator.calculateElvl(quote, elvl.value)).thenReturn(elvlValue)

        quoteService.save(quote)
        verify(elvlService, never()).merge(any())
    }

    @Test
    fun `save new quote and no old elvl then merge`() {
        val elvlValue = 100.2.toBigDecimal()
        val quote = Quote(isin = testIsin, bid = 100.2.toBigDecimal(), ask = 101.9.toBigDecimal())
        val elvl = Elvl(isin = testIsin, value = elvlValue)
        whenever(elvlService.getByIsin(testIsin)).thenReturn(null)
        whenever(elvlCalculator.calculateElvl(quote, null)).thenReturn(elvlValue)

        quoteService.save(quote)
        verify(elvlService, times(1)).merge(elvl)
    }

    @Test
    fun `save new quote and old elvl and new elvl is different then merge`() {
        val oldElvlValue = 100.2.toBigDecimal()
        val newElvlValue = 100.5.toBigDecimal()
        val quote = Quote(isin = testIsin, bid = 100.2.toBigDecimal(), ask = 101.9.toBigDecimal())
        val oldElvl = Elvl(isin = testIsin, value = oldElvlValue)
        val newElvl = Elvl(isin = testIsin, value = newElvlValue)
        whenever(elvlService.getByIsin(testIsin)).thenReturn(oldElvl)
        whenever(elvlCalculator.calculateElvl(quote, oldElvl.value)).thenReturn(newElvlValue)

        quoteService.save(quote)
        verify(elvlService, times(1)).merge(newElvl)
    }

    companion object {
        private const val testIsin = "AAAA9999BBBB"

    }

}
