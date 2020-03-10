package co.eliseev.quotes.controller

import co.eliseev.quotes.model.dto.QuoteDto
import co.eliseev.quotes.model.toModel
import co.eliseev.quotes.service.QuoteProcessor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/quotes")
class QuoteController(private val quoteProcessor: QuoteProcessor) {

    @PostMapping
    fun setToProcessingQueue(@RequestBody @Valid quoteDto: QuoteDto) =
        quoteProcessor.setToProcessingQueue(quoteDto.toModel())

}
