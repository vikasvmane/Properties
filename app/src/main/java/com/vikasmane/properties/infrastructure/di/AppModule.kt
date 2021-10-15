package com.vikasmane.properties.infrastructure.di

import android.content.Context
import com.vikasmane.properties.data.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext appContext: Context): LocalDataSource {
        return LocalDataSource(appContext)
    }
}