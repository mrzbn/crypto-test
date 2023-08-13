package com.freedom.cryptocurrency.data

import com.freedom.cryptocurrency.domain.CryptocurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptocurrencyDataModule {

    @Binds
    abstract fun bindCryptocurrencyRepository(impl: CryptocurrencyRepositoryImpl): CryptocurrencyRepository

}