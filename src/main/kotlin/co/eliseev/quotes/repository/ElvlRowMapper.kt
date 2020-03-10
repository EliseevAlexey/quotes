package co.eliseev.quotes.repository

import co.eliseev.quotes.model.entity.Elvl
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

object ElvlRowMapper : RowMapper<Elvl> {
    override fun mapRow(rs: ResultSet, rowNum: Int) =
        Elvl(
            isin = rs.getString("isin"),
            value = rs.getBigDecimal("value")
        )
}
