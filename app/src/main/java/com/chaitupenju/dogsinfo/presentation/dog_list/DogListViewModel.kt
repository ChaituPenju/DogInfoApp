package com.chaitupenju.dogsinfo.presentation.dog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitupenju.dogsinfo.common.Response
import com.chaitupenju.dogsinfo.domain.use_case.get_dogs.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase
): ViewModel() {
    
    private val _state = MutableStateFlow(DogListState())
    val state: StateFlow<DogListState> = _state

    init {
        getDogs()
    }

    private fun getDogs() {
        getDogsUseCase().onEach { result ->
            when (result) {
                is Response.Loading -> {
                    _state.value = DogListState(isLoading = true)
                }

                is Response.Success -> {
                    _state.value = DogListState(dogs = result.data ?: emptyList())
                }

                is Response.Error -> {
                    _state.value = DogListState(error = result.message ?: "An un-excepted error occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }

}