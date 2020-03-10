package co.eliseev.quotes.repository

import co.eliseev.quotes.model.entity.Elvl
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ElvlRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : ElvlRepository {

    override fun findAll(): List<Elvl> =
        jdbcTemplate.query(
            """
                SELECT
                    isin,
                    value
                FROM $tableName
            """.trimIndent(),
            ElvlRowMapper
        )

    override fun merge(elvl: Elvl) {
        jdbcTemplate.update(
            """
                MERGE INTO $tableName
                    KEY(isin)
                VALUES(:isin, :value)
            """.trimIndent(),
            mapOf(
                "isin" to elvl.isin,
                "value" to elvl.value
            )
        )
    }


    override fun findByIsin(isin: String) =
        jdbcTemplate.query(
            """
                SELECT DISTINCT
                    isin,
                    value
                FROM $tableName
                WHERE isin = :isin
            """.trimIndent(),
            mapOf("isin" to isin),
            ElvlRowMapper
        ).firstOrNull()

    companion object {
        const val tableName = "elvls"
    }

}
