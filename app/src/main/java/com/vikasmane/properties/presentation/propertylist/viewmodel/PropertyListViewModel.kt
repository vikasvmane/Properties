package com.vikasmane.properties.presentation.propertylist.viewmodel

import androidx.lifecycle.*
import com.vikasmane.properties.common.Resource
import com.vikasmane.properties.domain.interfaces.usecases.PropertyDataUseCase
import com.vikasmane.properties.presentation.propertylist.view.PropertyListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(private val propertyDataUseCase: PropertyDataUseCase) :
    ViewModel(),
    LifecycleObserver {

    private val _state = MutableLiveData(PropertyListState())
    val state: LiveData<PropertyListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        propertyDataUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PropertyListState(properties = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PropertyListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PropertyListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}