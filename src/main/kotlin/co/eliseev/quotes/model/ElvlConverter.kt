package co.eliseev.quotes.model

import co.eliseev.quotes.model.dto.ElvlDto
import co.eliseev.quotes.model.entity.Elvl

fun Elvl.toDto() = ElvlDto(isin, value)

fun List<Elvl>.toDto() = this.map { it.toDto() }
