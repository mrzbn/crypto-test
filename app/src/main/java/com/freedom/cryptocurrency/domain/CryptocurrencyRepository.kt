package com.freedom.cryptocurrency.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CryptocurrencyRepository {
    fun getCryptocurrencyPagingFlow(): Flow<PagingData<CryptocurrencyModel>>
}