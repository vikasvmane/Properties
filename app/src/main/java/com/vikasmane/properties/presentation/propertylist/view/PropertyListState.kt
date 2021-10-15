package com.vikasmane.properties.presentation.propertylist.view

import com.vikasmane.properties.domain.entities.Item

data class PropertyListState(
    val isLoading: Boolean = false,
    val properties: List<Item> = emptyList(),
    val error: String = ""
)
