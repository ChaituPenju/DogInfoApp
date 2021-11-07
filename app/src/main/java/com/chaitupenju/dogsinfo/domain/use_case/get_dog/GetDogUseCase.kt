package com.chaitupenju.dogsinfo.domain.use_case.get_dog

import com.chaitupenju.dogsinfo.common.Response
import com.chaitupenju.dogsinfo.data.network.dto.toDog
import com.chaitupenju.dogsinfo.data.network.dto.toDogInfo
import com.chaitupenju.dogsinfo.domain.model.Dog
import com.chaitupenju.dogsinfo.domain.model.DogInfo
import com.chaitupenju.dogsinfo.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDogUseCase @Inject constructor(
    private val repository: DogRepository
) {

    operator fun invoke(imageId: String): Flow<Response<DogInfo>> = flow {
        kotlin.runCatching {
            emit(Response.Loading())
            val dog = repository.getDogById(imageId).toDogInfo()
            emit(Response.Success(data = dog))
        }.onFailure {
            var errorMessage = "Some Error Occurred!"
            when (it) {
                is HttpException -> errorMessage = it.localizedMessage ?: errorMessage
                is IOException -> errorMessage = "Please check your internet connection!"
                else -> errorMessage = it.localizedMessage
            }
            emit(Response.Error<DogInfo>(errorMessage))
        }
    }
}