package com.example.myassignment.repository

import com.example.myassignment.model.PhotosModel
import retrofit2.Call
import retrofit2.http.GET

interface JsonPhotosClient {
    @GET("photos")
    fun getPhotos(): Call<List<PhotosModel>>
}
