package co.eliseev.quotes.repository

import co.eliseev.quotes.model.entity.Elvl

interface ElvlRepository {
    fun findAll(): List<Elvl>
    fun findByIsin(isin: String): Elvl?
    fun merge(elvl: Elvl)
}
