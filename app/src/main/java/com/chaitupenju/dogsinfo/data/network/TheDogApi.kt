package com.chaitupenju.dogsinfo.data.network

import com.chaitupenju.dogsinfo.data.network.dto.DogDto
import com.chaitupenju.dogsinfo.data.network.dto.DogInfoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheDogApi {

    @GET("/v1/breeds")
    suspend fun getDogs(
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null
    ): List<DogDto>

    @GET("/v1/images/{imageId}")
    suspend fun getDogById(@Path("imageId") imageId: String): DogInfoDto
}