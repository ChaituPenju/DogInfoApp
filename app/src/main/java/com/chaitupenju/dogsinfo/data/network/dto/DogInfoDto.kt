package com.chaitupenju.dogsinfo.data.network.dto


import com.chaitupenju.dogsinfo.domain.model.DogInfo
import com.google.gson.annotations.SerializedName

data class DogInfoDto(
    @SerializedName("breeds")
    val breeds: List<Breed>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
) {
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
}

data class Breed(
    @SerializedName("bred_for")
    val bredFor: String,
    @SerializedName("breed_group")
    val breedGroup: String,
    @SerializedName("height")
    val height: Height,
    @SerializedName("id")
    val id: Int,
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
)