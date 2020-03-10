package co.eliseev.quotes.service

import co.eliseev.quotes.model.entity.Elvl
import co.eliseev.quotes.repository.ElvlRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach

internal class ElvlServiceImplTest {

    private val elvlRepository = mock<ElvlRepository>()
    private lateinit var elvlService: ElvlService

    @BeforeEach
    fun reset() {
        reset(elvlRepository)
        elvlService = ElvlServiceImpl(elvlRepository)
    }

    @Test
    fun getAll() {
        val elvls = listOf(Elvl(isin = testIsin, value = 100.toBigDecimal()))
        whenever(elvlRepository.findAll()).thenReturn(elvls)

        assertEquals(elvls, elvlService.getAll())
    }

    @Test
    fun getByIsin() {
        val elvl = Elvl(isin = testIsin, value = 100.toBigDecimal())
        whenever(elvlRepository.findByIsin(testIsin)).thenReturn(elvl)

        assertEquals(elvl, elvlService.getByIsin(testIsin))
    }

    @Test
    fun merge() {
        val elvl = Elvl(isin = testIsin, value = 100.toBigDecimal())

        elvlService.merge(elvl)
        verify(elvlRepository, times(1)).merge(elvl)
    }

    companion object {
        private const val testIsin = "AAAA9999BBBB"
    }

}
