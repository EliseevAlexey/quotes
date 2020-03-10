package co.eliseev.quotes.controller

import co.eliseev.quotes.model.toDto
import co.eliseev.quotes.service.ElvlService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/elvls")
class ElvlController(private val elvlService: ElvlService) {

    @GetMapping("/all")
    fun getAll()= elvlService.getAll().toDto()

    @GetMapping("/{isin}")
    fun getElvlByIsin(@PathVariable("isin") isin: String)= elvlService.getByIsin(isin)?.toDto()

}
