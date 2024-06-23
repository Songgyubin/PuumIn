package com.gyub.network.di

import com.gyub.network.retrofit.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

/**
 * Retrofit API Service Module
 *
 * @author   Gyub
 * @created  2024/06/19
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun providesAuthService(retrofit: Retrofit.Builder): AuthService {
        return retrofit.build().create()
    }
}