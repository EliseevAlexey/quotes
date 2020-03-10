package co.eliseev.quotes.controller

import co.eliseev.quotes.service.ElvlService
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(controllers = [ElvlController::class])
internal class ElvlControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var elvlService: ElvlService

    @Test
    fun getAll() {
        mvc.get("/elvls/all")
            .andExpect { status { isOk } }
        verify(elvlService, times(1)).getAll()
    }

    @Test
    fun getElvlByIsin() {
        val isin = "AAAA0000BBBB"
        mvc.get("/elvls/{isin}", isin)
            .andExpect { status { isOk } }
        verify(elvlService, times(1)).getByIsin(isin)
    }

}
