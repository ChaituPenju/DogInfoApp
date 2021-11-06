package com.chaitupenju.dogsinfo.data.network.dto


import com.chaitupenju.dogsinfo.domain.model.Dog
import com.squareup.moshi.Json

data class DogDto(
    @field:Json(name = "bred_for")
    val bredFor: String,
    @field:Json(name = "breed_group")
    val breedGroup: String?,
    @field:Json(name = "height")
    val height: Height,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "image")
    val image: Image,
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
    fun DogDto.toDog(): Dog {
        return Dog(
            id = id,
            name = name,
            image = image.url,
            breedGroup = breedGroup ?: "",
            lifeSpan = lifeSpan,
            referenceImageId = referenceImageId
        )
    }

data class Height(
    @field:Json(name = "imperial")
    val imperial: String,
    @field:Json(name = "metric")
    val metric: String
)

data class Image(
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "width")
    val width: Int
)

data class Weight(
    @field:Json(name = "imperial")
    val imperial: String,
    @field:Json(name = "metric")
    val metric: String
)