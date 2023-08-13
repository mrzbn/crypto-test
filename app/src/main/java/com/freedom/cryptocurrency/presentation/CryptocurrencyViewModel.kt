package com.freedom.cryptocurrency.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.freedom.cryptocurrency.domain.CryptocurrencyRepository
import com.freedom.cryptocurrency.presentation.CryptocurrencyListItemState.Companion.toCryptocurrencyListItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyViewModel @Inject constructor(
    private val repository: CryptocurrencyRepository,
) : ViewModel() {

    fun getCryptocurrencyPagingFlow(): Flow<PagingData<CryptocurrencyListItemState>> =
        repository.getCryptocurrencyPagingFlow()
            .map { pagingData ->
                pagingData.map { it.toCryptocurrencyListItemState() }
            }
            .cachedIn(viewModelScope)

}