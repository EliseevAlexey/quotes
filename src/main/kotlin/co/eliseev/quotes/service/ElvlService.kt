package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Elvl
import co.eliseev.quotes.repository.ElvlRepository
import org.springframework.stereotype.Service

interface ElvlService {
    fun getAll(): List<Elvl>
    fun getByIsin(isin: String): Elvl?
    fun merge(elvl: Elvl)
}

@Service
class ElvlServiceImpl(private val elvlRepository: ElvlRepository) : ElvlService {

    override fun getAll() = elvlRepository.findAll()

    override fun getByIsin(isin: String) = elvlRepository.findByIsin(isin)

    override fun merge(elvl: Elvl) = elvlRepository.merge(elvl)

}
