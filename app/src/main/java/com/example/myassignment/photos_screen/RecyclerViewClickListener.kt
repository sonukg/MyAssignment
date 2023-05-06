package com.example.myassignment.photos_screen

import com.example.myassignment.model.PhotosModel

interface RecyclerViewClickListener {
    fun onItemClick(position: Int, photosList: List<PhotosModel>)
}
