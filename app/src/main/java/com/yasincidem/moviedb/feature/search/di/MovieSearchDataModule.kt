package com.yasincidem.moviedb.feature.search.di

import com.yasincidem.moviedb.feature.search.data.remote.MovieSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchDataModule {

    @Singleton
    @Provides
    fun provideMovieSearchService(retrofit: Retrofit): MovieSearchService =
        retrofit.create(MovieSearchService::class.java)
}