package com.freedom.cryptocurrency.presentation

sealed interface CryptocurrencyUIEvent {

    data class CryptocurrencyCheckChanged(
        val id: Int
    ): CryptocurrencyUIEvent

    object DeleteSelectionsClick: CryptocurrencyUIEvent

}