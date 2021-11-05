package com.chaitupenju.dogsinfo.data.repository

import com.chaitupenju.dogsinfo.data.network.TheDogApi
import com.chaitupenju.dogsinfo.data.network.dto.DogDto
import com.chaitupenju.dogsinfo.data.network.dto.DogInfoDto
import com.chaitupenju.dogsinfo.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val api: TheDogApi
): DogRepository {
    override suspend fun getDogs(): List<DogDto> {
        return api.getDogs(limit = 10, page = 1)
    }

    override suspend fun getDogById(imageId: String): DogInfoDto {
        return api.getDogById(imageId = imageId)
    }


}