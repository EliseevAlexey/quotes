package co.eliseev.quotes.repository

import co.eliseev.quotes.model.entity.Quote
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository

interface QuoteRepository {
    fun save(quote: Quote)
    fun getAll(): List<Quote>
}

@Repository
class QuoteRepositoryImpl(private val jdbcTemplate: NamedParameterJdbcTemplate) : QuoteRepository {

    override fun save(quote: Quote) {
        SimpleJdbcInsert(jdbcTemplate.jdbcTemplate)
            .withTableName(tableName)
            .execute(
                mapOf(
                    "isin" to quote.isin,
                    "bid" to quote.bid,
                    "ask" to quote.ask,
                    "bidSize" to quote.bidSize,
                    "askSize" to quote.askSize
                )
            )
    }

    override fun getAll(): List<Quote> =
        jdbcTemplate.query(
            """
                SELECT
                    id,
                    isin,
                    bid,
                    ask,
                    bidSize,
                    askSize
                FROM $tableName
            """.trimIndent(),
            QuoteRowMapper
        )

    companion object {
        private const val tableName = "quotes"
    }

}
