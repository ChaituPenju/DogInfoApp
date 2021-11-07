package com.chaitupenju.dogsinfo.domain.model

data class DogInfo(
    val id: Int,
    val name: String,
    val bredFor: String,
    val breedGroup: String,
    val lifeSpan: String,
    val imageUrl: String,
    val temperament: List<String>
)
