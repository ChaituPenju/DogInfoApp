package com.chaitupenju.dogsinfo.presentation.dog_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitupenju.dogsinfo.common.Constants
import com.chaitupenju.dogsinfo.common.Response
import com.chaitupenju.dogsinfo.domain.use_case.get_dog.GetDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DogInfoViewModel @Inject constructor(
    private val getDogUseCase: GetDogUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    
    private val _state = MutableStateFlow(DogInfoState())
    val state: StateFlow<DogInfoState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_DOG_IMAGE_ID)?.let { imageId ->
            getDogInfo(imageId)
        }
    }

    private fun getDogInfo(imageId: String) {
        getDogUseCase(imageId).onEach { result ->
            when (result) {
                is Response.Loading -> {
                    _state.value = DogInfoState(isLoading = true)
                }

                is Response.Success -> {
                    _state.value = DogInfoState(dog = result.data)
                }

                is Response.Error -> {
                    _state.value = DogInfoState(error = result.message ?: "An un-excepted error occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }

}