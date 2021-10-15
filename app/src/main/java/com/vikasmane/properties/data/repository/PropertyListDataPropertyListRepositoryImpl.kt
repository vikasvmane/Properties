package com.vikasmane.properties.data.repository

import com.vikasmane.properties.data.local.LocalDataSource
import com.vikasmane.properties.domain.entities.Item
import com.vikasmane.properties.domain.interfaces.repositories.PropertyListRepository
import javax.inject.Inject

class PropertyListDataPropertyListRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    PropertyListRepository {
    override suspend fun getProperties(): List<Item> {
        return localDataSource.getLocalData().items
    }
}