package com.chaitupenju.dogsinfo.presentation.dog_list

import com.chaitupenju.dogsinfo.domain.model.Dog

data class DogListState(
    val isLoading: Boolean = false,
    val dogs: List<Dog> = emptyList(),
    val error: String = ""
)