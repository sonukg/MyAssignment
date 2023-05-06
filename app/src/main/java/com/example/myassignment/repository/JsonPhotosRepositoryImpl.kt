package com.example.myassignment.repository

import com.example.myassignment.model.PhotosModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class JsonPhotosRepositoryImpl @Inject constructor(
    retrofit: Retrofit,
) : JsonPhotosRepository {
    private val jsonPhotosClient = retrofit.create(JsonPhotosClient::class.java)

    override fun getPhotos(callback: Callback<List<PhotosModel>?>) {
        jsonPhotosClient.getPhotos().enqueue(
            object : retrofit2.Callback<List<PhotosModel>?> {
                override fun onResponse(
                    call: Call<List<PhotosModel>?>,
                    response: Response<List<PhotosModel>?>,
                ) {
                    callback.onSuccess(response.body())
                }

                override fun onFailure(call: Call<List<PhotosModel>?>, t: Throwable) {
                    callback.onError(errorCode = t.message, errorMessage = t.message)
                }
            },
        )
    }
}
