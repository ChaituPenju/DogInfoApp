package com.chaitupenju.dogsinfo.di

import com.chaitupenju.dogsinfo.BuildConfig
import com.chaitupenju.dogsinfo.common.Constants
import com.chaitupenju.dogsinfo.data.network.TheDogApi
import com.chaitupenju.dogsinfo.data.repository.DogRepositoryImpl
import com.chaitupenju.dogsinfo.domain.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTheDogApi(): TheDogApi {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        return Retrofit.Builder()
            .baseUrl(Constants.DOG_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(TheDogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDogRepository(api: TheDogApi): DogRepository {
        return DogRepositoryImpl(api)
    }
}