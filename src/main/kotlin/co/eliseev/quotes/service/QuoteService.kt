package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Quote

interface QuoteService {
    fun save(quote: Quote)
}
