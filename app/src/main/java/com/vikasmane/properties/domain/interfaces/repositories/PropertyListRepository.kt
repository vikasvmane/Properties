package com.vikasmane.properties.domain.interfaces.repositories

import com.vikasmane.properties.domain.entities.Item

/**
 * Contract to construct repository implementation
 */
interface PropertyListRepository {
    suspend fun getProperties(): List<Item>
}