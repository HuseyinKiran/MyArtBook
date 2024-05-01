package com.huseyinkiran.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.huseyinkiran.artbooktesting.model.ImageResponse
import com.huseyinkiran.artbooktesting.roomdb.Art
import com.huseyinkiran.artbooktesting.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>

}