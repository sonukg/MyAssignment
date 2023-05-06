package com.example.myassignment.photos_screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myassignment.databinding.ActivityPhotoDetailsBinding
import com.example.myassignment.model.PhotosModel
import com.squareup.picasso.Picasso

class PhotoDetails : AppCompatActivity() {
    private val _binding: ActivityPhotoDetailsBinding by lazy { ActivityPhotoDetailsBinding.inflate(layoutInflater) }
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
        setDataToUI()
    }

    private fun setDataToUI() {
        var data = intent.extras
        var photos = data?.getParcelable<PhotosModel>("Photos")
        Picasso.get().load(photos?.url).into(_binding.img)
        _binding.textTitle.setText(photos?.title)
    }
}
