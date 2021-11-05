package com.chaitupenju.dogsinfo.di

import com.chaitupenju.dogsinfo.common.Constants
import com.chaitupenju.dogsinfo.data.network.TheDogApi
import com.chaitupenju.dogsinfo.data.repository.DogRepositoryImpl
import com.chaitupenju.dogsinfo.domain.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTheDogApi(): TheDogApi {
        return Retrofit.Builder()
            .baseUrl(Constants.DOG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheDogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDogRepository(api: TheDogApi): DogRepository {
        return DogRepositoryImpl(api)
    }
}