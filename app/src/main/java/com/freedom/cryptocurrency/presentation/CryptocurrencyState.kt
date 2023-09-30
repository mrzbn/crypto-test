package com.freedom.cryptocurrency.presentation

import com.freedom.cryptocurrency.domain.CryptocurrencyModel
import java.text.DecimalFormat

data class CryptocurrencyListItemState(
    val id: Int,
    val nameFa: String,
    val symbol: String,
    val price: String,
) {

    companion object {

        fun CryptocurrencyModel.toCryptocurrencyListItemState() = CryptocurrencyListItemState(
            id = id,
            nameFa = nameFa,
            symbol = symbol,
            price = price.take(8)
        )

    }

}