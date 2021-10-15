package com.vikasmane.properties.data.local

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.vikasmane.properties.common.Constants
import com.vikasmane.properties.domain.entities.PropertyModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val context: Context) {
    fun getLocalData(): PropertyModel {
        val gson = Gson()
        return gson.fromJson(
            context.assets.readAssetsFile(Constants.ASSET_NAME),
            PropertyModel::class.java
        )
    }

    private fun AssetManager.readAssetsFile(fileName: String): String =
        open(fileName).bufferedReader().use { it.readText() }
}