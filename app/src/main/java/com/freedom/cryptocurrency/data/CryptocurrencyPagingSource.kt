package com.freedom.cryptocurrency.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.freedom.cryptocurrency.domain.CryptocurrencyModel
import javax.inject.Inject

class CryptocurrencyPagingSource @Inject constructor(
    private val dataSource: CryptocurrencyDataSource
) : PagingSource<Int, CryptocurrencyModel>() {

    override fun getRefreshKey(state: PagingState<Int, CryptocurrencyModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptocurrencyModel> {
        return try {
            val page = params.key ?: 0
            val response = dataSource.getPrices()

            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}