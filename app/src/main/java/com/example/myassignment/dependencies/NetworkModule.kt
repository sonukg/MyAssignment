package com.example.myassignment.dependencies

import com.example.myassignment.BuildConfig
import com.example.myassignment.repository.JsonPhotosRepository
import com.example.myassignment.repository.JsonPhotosRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        gsonConverter: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    @Singleton
    fun provideJsonPhotosRepository(retrofit: Retrofit): JsonPhotosRepository {
        return JsonPhotosRepositoryImpl(retrofit = retrofit)
    }
}
