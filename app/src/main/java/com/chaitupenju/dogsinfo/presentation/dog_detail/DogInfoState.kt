package com.chaitupenju.dogsinfo.presentation.dog_detail

import com.chaitupenju.dogsinfo.domain.model.Dog
import com.chaitupenju.dogsinfo.domain.model.DogInfo

data class DogInfoState(
    val isLoading: Boolean = false,
    val dog: DogInfo? = null,
    val error: String = ""
)