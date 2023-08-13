package com.freedom.cryptocurrency.network

import com.freedom.cryptocurrency.data.CryptocurrencyDataSource
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CryptocurrencyNetworkModule {

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: MoshiConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.tabdeal.ir/")
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideCryptocurrencyService(retrofit: Retrofit): CryptocurrencyService =
        retrofit.create(CryptocurrencyService::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptocurrencyNetworkBindingModule {

    @Binds
    abstract fun bind(impl: CryptocurrencyDataSourceImpl): CryptocurrencyDataSource

}