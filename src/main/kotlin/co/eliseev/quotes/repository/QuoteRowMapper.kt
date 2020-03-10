package co.eliseev.quotes.repository

import co.eliseev.quotes.model.entity.Quote
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

object QuoteRowMapper : RowMapper<Quote> {
    override fun mapRow(rs: ResultSet, rowNum: Int) =
        Quote(
            id = rs.getLong("id"),
            isin = rs.getString("isin"),
            bid = rs.getBigDecimal("bid"),
            ask = rs.getBigDecimal("ask"),
            bidSize = rs.getBigDecimal("bidSize"),
            askSize = rs.getBigDecimal("askSize")
        )
}
