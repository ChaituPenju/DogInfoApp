package com.chaitupenju.dogsinfo.data.network.dto


import com.chaitupenju.dogsinfo.domain.model.Dog
import com.google.gson.annotations.SerializedName

data class DogDto(
    @SerializedName("bred_for")
    val bredFor: String,
    @SerializedName("breed_group")
    val breedGroup: String,
    @SerializedName("height")
    val height: Height,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Image,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("reference_image_id")
    val referenceImageId: String,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("weight")
    val weight: Weight
) {
    fun DogDto.toDog(): Dog {
        return Dog(
            id = id,
            name = name,
            image = image.url,
            breedGroup = breedGroup,
            lifeSpan = lifeSpan,
            referenceImageId = referenceImageId
        )
    }
}

data class Height(
    @SerializedName("imperial")
    val imperial: String,
    @SerializedName("metric")
    val metric: String
)

data class Image(
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class Weight(
    @SerializedName("imperial")
    val imperial: String,
    @SerializedName("metric")
    val metric: String
)