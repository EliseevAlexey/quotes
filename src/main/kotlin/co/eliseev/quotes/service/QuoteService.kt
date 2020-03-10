package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Elvl
import co.eliseev.quotes.model.entity.Quote
import co.eliseev.quotes.repository.QuoteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

interface QuoteService {
    fun save(quote: Quote)
}

@Service
class QuoteServiceImpl(
    private val quoteRepository: QuoteRepository,
    private val elvlService: ElvlService,
    private val elvlCalculator: ElvlCalculator
) : QuoteService {

    @Transactional
    override fun save(quote: Quote) = quoteRepository.save(quote.also { adjustElvl(it) })

    private fun adjustElvl(quote: Quote) {
        val isin = quote.isin
        withLockBy(isin) {
            val oldElvl = elvlService.getByIsin(isin)?.value
            val newElvl = elvlCalculator.calculateElvl(quote, oldElvl)
            if (oldElvl != newElvl) elvlService.merge(Elvl(isin, newElvl))
        }
    }

    private fun withLockBy(isin: String, action: () -> Unit) = getLock(isin).withLock(action)

    private fun getLock(isin: String) = IsinLock.locks.getOrPut(isin) { ReentrantLock() }

}
