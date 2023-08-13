package com.freedom.cryptocurrency.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.freedom.cryptocurrency.domain.CryptocurrencyModel
import com.freedom.cryptocurrency.domain.CryptocurrencyRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class CryptocurrencyRepositoryImpl @Inject constructor(
    private val pagingSource: CryptocurrencyPagingSource
) : CryptocurrencyRepository {

    override fun getCryptocurrencyPagingFlow(): Flow<PagingData<CryptocurrencyModel>> {
        return Pager(config = PagingConfig(
            pageSize = 20,
        ), pagingSourceFactory = { pagingSource }).flow
    }

}