package com.chaitupenju.dogsinfo.domain.use_case.get_dogs

import com.chaitupenju.dogsinfo.common.Response
import com.chaitupenju.dogsinfo.data.network.dto.toDog
import com.chaitupenju.dogsinfo.domain.model.Dog
import com.chaitupenju.dogsinfo.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
    private val repository: DogRepository
) {

    operator fun invoke(): Flow<Response<List<Dog>>> = flow {
        kotlin.runCatching {
            emit(Response.Loading())
            val dogs = repository.getDogs().map { it.toDog() }
            emit(Response.Success(data = dogs))
        }.onFailure {
            var errorMessage = "Some Error Occurred!"

            errorMessage = when (it) {
                is HttpException -> it.localizedMessage ?: "Some Http Error Occurred!"
                is IOException -> "Please check your internet connection!"
                else -> it.localizedMessage ?: errorMessage
            }
            emit(Response.Error<List<Dog>>(errorMessage))
        }
    }
}