package com.vikasmane.properties.infrastructure.di

import com.vikasmane.properties.data.repository.PropertyListDataPropertyListRepositoryImpl
import com.vikasmane.properties.domain.interfaces.repositories.PropertyListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePropertyListRepository(propertyListDataRepositoryImpl: PropertyListDataPropertyListRepositoryImpl): PropertyListRepository

}