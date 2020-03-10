package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Elvl

interface ElvlService {
    fun getAll(): List<Elvl>
    fun getByIsin(isin: String): Elvl?
    fun merge(elvl: Elvl)
}
