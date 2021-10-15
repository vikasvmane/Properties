package com.vikasmane.properties.domain.usecases

import com.vikasmane.properties.common.Resource
import com.vikasmane.properties.domain.entities.Item
import com.vikasmane.properties.domain.interfaces.repositories.PropertyListRepository
import com.vikasmane.properties.domain.interfaces.usecases.PropertyDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * Property data Usecase implementation
 */
class PropertyDataUseCaseImpl @Inject constructor(private val propertyListRepository: PropertyListRepository) :
    PropertyDataUseCase {
    override fun invoke(): Flow<Resource<List<Item>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = propertyListRepository.getProperties().map { it }
            emit(Resource.Success(coins))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}