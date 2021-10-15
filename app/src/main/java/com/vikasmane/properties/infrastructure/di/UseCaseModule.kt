package com.vikasmane.properties.infrastructure.di

import com.vikasmane.properties.domain.interfaces.usecases.PropertyDataUseCase
import com.vikasmane.properties.domain.usecases.PropertyDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun providePropertyListUseCase(propertyDataUseCaseImpl: PropertyDataUseCaseImpl): PropertyDataUseCase
}