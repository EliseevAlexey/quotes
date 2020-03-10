package co.eliseev.quotes.model

import co.eliseev.quotes.model.dto.QuoteDto
import co.eliseev.quotes.model.entity.Quote

fun QuoteDto.toModel() =
    QuoteModel(
        id = id,
        isin = isin,
        bid = bid,
        ask = ask,
        bidSize = bidSize,
        askSize = askSize
    )

fun QuoteModel.toEntity() =
    Quote(
        id = id,
        isin = isin,
        bid = bid,
        ask = ask,
        bidSize = bidSize,
        askSize = askSize
    )
