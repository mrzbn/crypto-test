package com.freedom.cryptocurrency.data

import com.freedom.cryptocurrency.domain.CryptocurrencyModel

interface CryptocurrencyDataSource {
    suspend fun getPrices(): List<CryptocurrencyModel>
}