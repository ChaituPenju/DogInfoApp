package com.chaitupenju.dogsinfo.data.network.dto


import com.chaitupenju.dogsinfo.domain.model.DogInfo
import com.squareup.moshi.Json

data class DogInfoDto(
    @field:Json(name = "breeds")
    val breeds: List<Breed>,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "width")
    val width: Int
)

data class Breed(
    @field:Json(name = "bred_for")
    val bredFor: String,
    @field:Json(name = "breed_group")
    val breedGroup: String?,
    @field:Json(name = "height")
    val height: Height,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "life_span")
    val lifeSpan: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "reference_image_id")
    val referenceImageId: String,
    @field:Json(name = "temperament")
    val temperament: String,
    @field:Json(name = "weight")
    val weight: Weight
)

fun DogInfoDto.toDogInfo(): DogInfo {
    with(breeds[0]) {
        return DogInfo(
            id = id,
            name = name,
            bredFor = bredFor,
            breedGroup = breedGroup ?: "No Info Available!",
            lifeSpan = lifeSpan,
            imageUrl = url,
            temperament = temperament.split(", ")
        )
    }
}