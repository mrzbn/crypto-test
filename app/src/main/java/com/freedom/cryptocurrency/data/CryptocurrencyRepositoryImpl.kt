package com.freedom.cryptocurrency.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.freedom.cryptocurrency.domain.CryptocurrencyModel
import com.freedom.cryptocurrency.domain.CryptocurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptocurrencyRepositoryImpl @Inject constructor(
    private val dataSource: CryptocurrencyDataSource,
) : CryptocurrencyRepository {

    override fun getCryptocurrencyPagingFlow(): Flow<PagingData<CryptocurrencyModel>> {
        return Pager(config = PagingConfig(
            pageSize = 20,
        ), pagingSourceFactory = {
            CryptocurrencyPagingSource(dataSource = dataSource)
        }).flow
    }

}