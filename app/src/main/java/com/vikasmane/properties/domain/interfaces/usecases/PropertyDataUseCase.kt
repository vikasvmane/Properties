package com.vikasmane.properties.domain.interfaces.usecases

import com.vikasmane.properties.common.Resource
import com.vikasmane.properties.domain.entities.Item
import kotlinx.coroutines.flow.Flow

/**
 * Use case contract for fetching property data
 */
interface PropertyDataUseCase {
    operator fun invoke(): Flow<Resource<List<Item>>>
}