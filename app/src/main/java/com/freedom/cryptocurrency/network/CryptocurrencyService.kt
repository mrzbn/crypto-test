package com.freedom.cryptocurrency.network

import com.freedom.cryptocurrency.domain.CryptocurrencyModel
import retrofit2.http.Body
import retrofit2.http.POST

interface CryptocurrencyService {

    @POST("r/plots/currency_prices")
    suspend fun getPrices(): List<CryptocurrencyModel>

}