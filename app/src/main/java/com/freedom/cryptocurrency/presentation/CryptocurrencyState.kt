package com.freedom.cryptocurrency.presentation

import com.freedom.cryptocurrency.domain.CryptocurrencyModel

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
            price = String.format("%.2s", price)
        )

    }

}