package com.example.myassignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotosModel(
    @SerializedName(ALBUM_ID)
    var albumId: Int = 0,
    @SerializedName(ID)
    var id: Int = 0,
    @SerializedName(TITLE)
    var title: String? = null,
    @SerializedName(URL)
    var url: String? = null,
    @SerializedName(THUMBNAIL_URL)
    var thumbnailUrl: String? = null,

) : Parcelable {
    private companion object {
        const val ALBUM_ID = "albumId"
        const val ID = "id"
        const val TITLE = "title"
        const val URL = "url"
        const val THUMBNAIL_URL = "thumbnailUrl"
    }
}
