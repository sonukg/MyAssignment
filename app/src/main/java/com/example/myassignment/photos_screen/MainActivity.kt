package com.example.myassignment.photos_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myassignment.databinding.ActivityMainBinding
import com.example.myassignment.model.PhotosModel
import com.example.myassignment.photos_screen.photosadapter.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    private val _binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainActivityViewModel by viewModels()
    private val photosAdapter = PhotosAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)
        viewModel.fetchPhotos()
        setUpUi()
        setObservables()
    }

    private fun setUpUi() {
        with(_binding.rvPhotos) {
            val linearLayoutManager = LinearLayoutManager(this.context)
            layoutManager = linearLayoutManager
            adapter = photosAdapter
            val decoration = DividerItemDecoration(this.context, linearLayoutManager.orientation)
            addItemDecoration(decoration)
        }
    }

    private fun setObservables() {
        with(viewModel) {
            photosDetails.observe(this@MainActivity) {
                it?.let { photos ->
                    photosAdapter.addNewData(photos)
                }
            }
        }
    }

    override fun onItemClick(position: Int, photosList: List<PhotosModel>) {
//        TODO navigate to other screen on click
        val intent = Intent(this, PhotoDetails::class.java)
        intent.putExtra("Photos", photosList.get(position))
        startActivity(intent)
        Log.i("MainActivity", "onItemClick: $position")
    }
}
