package com.freedom.cryptocurrency.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptocurrencyModel(
    @Json(name ="id")
    val id: Int,
    @Json(name ="name_fa")
    val nameFa: String,
    @Json(name ="symbol")
    val symbol: String,
    @Json(name ="price_in_usdt")
    val price: String
)