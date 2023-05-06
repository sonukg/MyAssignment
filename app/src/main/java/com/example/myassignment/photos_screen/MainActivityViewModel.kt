package com.example.myassignment.photos_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassignment.model.PhotosModel
import com.example.myassignment.repository.Callback
import com.example.myassignment.repository.JsonPhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: JsonPhotosRepository,
) : ViewModel() {
    private val TAG = this::class.java.simpleName

    var photosDetails: MutableLiveData<List<PhotosModel>> = MutableLiveData(listOf())
        private set
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun fetchPhotos() {
        repository.getPhotos(
            object : Callback<List<PhotosModel>?> {
                override fun onSuccess(response: List<PhotosModel>?) {
                    response?.let {
                        photosDetails.value = it
                    }
                }

                override fun onError(errorCode: String?, errorMessage: String?) {
                    this@MainActivityViewModel.errorMessage.value = errorMessage
                }
            },
        )
    }
}
