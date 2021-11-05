package com.chaitupenju.dogsinfo.domain.repository

import com.chaitupenju.dogsinfo.data.network.dto.DogDto
import com.chaitupenju.dogsinfo.data.network.dto.DogInfoDto

interface DogRepository {

    suspend fun getDogs(): List<DogDto>

    suspend fun getDogById(imageId: String): DogInfoDto
}