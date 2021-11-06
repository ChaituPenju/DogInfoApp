package com.chaitupenju.dogsinfo.data.network.dto


import com.chaitupenju.dogsinfo.domain.model.DogInfo
import com.squareup.moshi.Json

data class DogInfoDto(
    @Json(name = "breeds")
    val breeds: List<Breed>,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "width")
    val width: Int
)
    fun DogInfoDto.toDogInfo(): DogInfo {
        with(breeds[0]) {
            return DogInfo(
                id = id,
                name = name,
                bredFor = bredFor,
                breedGroup = breedGroup,
                lifeSpan = lifeSpan,
                referenceImageId = referenceImageId,
                temperament = temperament.split(", ")
            )
        }
    }

data class Breed(
    @Json(name = "bred_for")
    val bredFor: String,
    @Json(name = "breed_group")
    val breedGroup: String,
    @Json(name = "height")
    val height: Height,
    @Json(name = "id")
    val id: Int,
    @Json(name = "life_span")
    val lifeSpan: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "reference_image_id")
    val referenceImageId: String,
    @Json(name = "temperament")
    val temperament: String,
    @Json(name = "weight")
    val weight: Weight
)