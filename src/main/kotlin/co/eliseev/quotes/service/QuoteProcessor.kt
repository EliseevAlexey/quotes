package co.eliseev.quotes.service

import co.eliseev.quotes.model.QuoteModel

interface QuoteProcessor {
    fun setToProcessingQueue(quoteModel: QuoteModel)
}
