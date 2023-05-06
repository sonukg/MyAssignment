package com.example.myassignment.repository

import com.example.myassignment.model.PhotosModel
import io.mockk.CapturingSlot
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class JsonPhotosRepositoryImplTest {

    @MockK
    private lateinit var retrofit: Retrofit

    @MockK
    private lateinit var photosClient: JsonPhotosClient

    @MockK
    private lateinit var photosCallback: Callback<List<PhotosModel>?>

    private val photosCaptureCallback = CapturingSlot<retrofit2.Callback<List<PhotosModel>?>>()

    private lateinit var jsonPhotosRepositoryImpl: JsonPhotosRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
        every { retrofit.create(JsonPhotosClient::class.java) } returns photosClient
        jsonPhotosRepositoryImpl = JsonPhotosRepositoryImpl(retrofit = retrofit)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getPhotos api call test`() {
        jsonPhotosRepositoryImpl.getPhotos(photosCallback)
        verify {
            photosClient.getPhotos()
                .enqueue(capture(photosCaptureCallback))
        }
    }

    @Test
    fun `getPhotos api call success test`() {
        val photos = listOf(
            PhotosModel(
                title = TITLE,
                url = URL,
            ),
            PhotosModel(
                title = TITLE,
                url = URL,
            ),
        )
        val call = mockk<Call<List<PhotosModel>?>>()
        val response = mockk<Response<List<PhotosModel>?>>()
        every { response.body() } returns photos
        `getPhotos api call test`()
        photosCaptureCallback.captured.onResponse(call, response)
        verify { photosCallback.onSuccess(photos) }
    }

    @Test
    fun `getPhotos api call error test`() {
        val call = mockk<Call<List<PhotosModel>?>>()
        val throwable = mockk<Throwable>()
        every { throwable.message } returns ERROR
        `getPhotos api call test`()
        photosCaptureCallback.captured.onFailure(call, throwable)
        verify { photosCallback.onError(errorCode = ERROR, errorMessage = ERROR) }
    }

    private companion object {
        const val TITLE = "title"
        const val URL = "google.com"
        const val ERROR = "Error"
    }
}
