package co.eliseev.quotes.service

import co.eliseev.quotes.model.QuoteModel
import co.eliseev.quotes.model.toEntity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class QuoteProcessorImplTest{

    private val quoteService = mock<QuoteService>()
    private lateinit var quoteProcessor: QuoteProcessor

    @BeforeEach
    fun reset() {
        reset(quoteService)
        quoteProcessor = QuoteProcessorImpl(quoteService)
    }

    @Test
    fun setToQueue() {
        val quoteModel = QuoteModel(isin = "AAAA9999BBBB", ask = 100.toBigDecimal())
        quoteProcessor.setToProcessingQueue(quoteModel)
        Thread.sleep(100L) // FIXME launch processing delay
        verify(quoteService, times(1)).save(quoteModel.toEntity())
    }

}
