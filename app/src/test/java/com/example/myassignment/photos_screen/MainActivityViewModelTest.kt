package com.example.myassignment.photos_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myassignment.model.PhotosModel
import com.example.myassignment.repository.Callback
import com.example.myassignment.repository.JsonPhotosRepository
import io.mockk.CapturingSlot
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: JsonPhotosRepository

    private val photoCaptureSlot = CapturingSlot<Callback<List<PhotosModel>?>>()

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
        mainActivityViewModel = MainActivityViewModel(repository = repository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `fetchPhotos api call test`() {
        mainActivityViewModel.fetchPhotos()
        verify { repository.getPhotos(capture(photoCaptureSlot)) }
    }

    @Test
    fun `fetchPhotos api call success test`() {
        val response = listOf(
            PhotosModel(
                title = TITLE,
                url = URL,
            ),
            PhotosModel(
                title = TITLE,
                url = URL,
            ),
        )
        `fetchPhotos api call test`()
        photoCaptureSlot.captured.onSuccess(response)
        assertEquals(response, mainActivityViewModel.photosDetails.value)
    }

    @Test
    fun `fetchPhotos api call error test`() {
        `fetchPhotos api call test`()
        photoCaptureSlot.captured.onError(errorCode = ERROR, errorMessage = ERROR)
        assertEquals(ERROR, mainActivityViewModel.errorMessage.value)
    }

    private companion object {
        const val TITLE = "title"
        const val URL = "google.com"
        const val ERROR = "Error"
    }
}
