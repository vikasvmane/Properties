package com.vikasmane.properties.presentation.propertydetails

import com.vikasmane.properties.domain.entities.Item

/**
 * Propagates property item click event
 */
interface PropertyItemClickListener {
    fun onPropertyClicked(item: Item)
}