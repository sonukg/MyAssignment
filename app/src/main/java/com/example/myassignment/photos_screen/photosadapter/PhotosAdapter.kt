package com.example.myassignment.photos_screen.photosadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myassignment.databinding.AdapterPhotosBinding
import com.example.myassignment.model.PhotosModel
import com.example.myassignment.photos_screen.RecyclerViewClickListener
import com.squareup.picasso.Picasso

class PhotosAdapter(private val clickListener: RecyclerViewClickListener) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    private var photosData: List<PhotosModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = AdapterPhotosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bindData(photosData[holder.adapterPosition])
        holder.itemView.setOnClickListener { clickListener.onItemClick(holder.adapterPosition, photosData) }
    }

    override fun getItemCount(): Int = photosData.size

    fun addNewData(photosList: List<PhotosModel>) {
        photosData = photosList
        notifyDataSetChanged()
    }

    inner class PhotosViewHolder(private val item: AdapterPhotosBinding) : RecyclerView.ViewHolder(item.root) {
        fun bindData(photosModel: PhotosModel) {
            with(item) {
                tvTitle.text = photosModel.title
                tvDesc.text = photosModel.thumbnailUrl
                Picasso.get().load(photosModel.url).into(photoImage)
            }
        }
    }
}
