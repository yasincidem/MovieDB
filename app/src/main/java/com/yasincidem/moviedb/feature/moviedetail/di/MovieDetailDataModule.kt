package com.yasincidem.moviedb.feature.moviedetail.di

import com.yasincidem.moviedb.feature.moviedetail.data.remote.MovieDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailDataModule {

    @Singleton
    @Provides
    fun provideMovieDetailService(retrofit: Retrofit): MovieDetailService =
        retrofit.create(MovieDetailService::class.java)
}