package co.eliseev.quotes.service

import co.eliseev.quotes.model.QuoteModel
import co.eliseev.quotes.model.toEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentLinkedQueue

@Service
class QuoteProcessorImpl(private val quoteService: QuoteService) : QuoteProcessor {

    private val queue = ConcurrentLinkedQueue<QuoteModel>()

    override fun setToProcessingQueue(quoteModel: QuoteModel) {
        queue.add(quoteModel)
        GlobalScope.launch { process() }
    }

    private fun process() {
        while (true) {
            if (queue.isNotEmpty()) pollAndSave() else return
        }
    }

    private fun pollAndSave() =
        queue.poll().toEntity()
            .also { quoteService.save(it) }

}
