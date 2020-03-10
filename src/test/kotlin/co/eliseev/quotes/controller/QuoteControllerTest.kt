package co.eliseev.quotes.controller

import co.eliseev.quotes.model.dto.QuoteDto
import co.eliseev.quotes.model.toModel
import co.eliseev.quotes.service.QuoteProcessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(controllers = [QuoteController::class])
internal class QuoteControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var quoteProcessor: QuoteProcessor

    @Test
    fun save() {
        val isin = "AAAA9999BBBB"
        val bid = 100.2.toBigDecimal()
        val ask = 101.9.toBigDecimal()
        val quoteDto = QuoteDto(isin = isin, bid = bid, ask = ask)

        mvc.post("/quotes") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(quoteDto)
        }.andExpect {
            status { isOk }
        }
        verify(quoteProcessor, times(1)).setToProcessingQueue(quoteDto.toModel())
    }

    companion object {
        private val mapper = ObjectMapper()
    }

}
