package com.freedom.cryptocurrency.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.freedom.cryptocurrency.data.CryptocurrencyDataSource
import com.freedom.cryptocurrency.data.CryptocurrencyPagingSource
import com.freedom.cryptocurrency.domain.CryptocurrencyModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptocurrencyDataSourceImpl @Inject constructor(
    private val service: CryptocurrencyService
) : CryptocurrencyDataSource {

    override suspend fun getPrices(): List<CryptocurrencyModel> {
        return service.getPrices()
    }

}
