package com.gyub.data.di

import com.gyub.data.auth.repository.NetworkAuthRepository
import com.gyub.data.quotes.repository.NetworkQuotesRepository
import com.gyub.domain.auth.repository.AuthRepository
import com.gyub.domain.quotes.repository.QuotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repository Module
 *
 * @author   Gyub
 * @created  2024/06/20
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsAuthRepository(
        networkAuthRepository: NetworkAuthRepository,
    ): AuthRepository

    @Singleton
    @Binds
    fun bindsQuotesRepository(
        networkQuotesRepository: NetworkQuotesRepository,
    ): QuotesRepository
}