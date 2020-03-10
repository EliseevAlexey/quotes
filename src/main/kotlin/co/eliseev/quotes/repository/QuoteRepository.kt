package co.eliseev.quotes.repository

import co.eliseev.quotes.model.entity.Quote

interface QuoteRepository {
    fun save(quote: Quote)
    fun getAll(): List<Quote>
}
