package com.chaitupenju.dogsinfo.domain.model

data class Dog(
    val id: Int,
    val name: String,
    val image: String,
    val breedGroup: String,
    val lifeSpan: String,
    val referenceImageId: String
)
