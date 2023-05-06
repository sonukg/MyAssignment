package com.example.myassignment.repository

import com.example.myassignment.model.PhotosModel

interface JsonPhotosRepository {
    fun getPhotos(callback: Callback<List<PhotosModel>?>)
}
