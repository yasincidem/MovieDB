package com.yasincidem.moviedb.feature.trending.di

import com.yasincidem.moviedb.feature.trending.data.remote.TrendingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object TrendingDataModule {

    @Singleton
    @Provides
    fun provideTrendingService(retrofit: Retrofit): TrendingService =
        retrofit.create(TrendingService::class.java)
}