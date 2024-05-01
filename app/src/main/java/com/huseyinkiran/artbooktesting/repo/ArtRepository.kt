package com.huseyinkiran.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.huseyinkiran.artbooktesting.api.RetrofitAPI
import com.huseyinkiran.artbooktesting.model.ImageResponse
import com.huseyinkiran.artbooktesting.roomdb.Art
import com.huseyinkiran.artbooktesting.roomdb.ArtDao
import com.huseyinkiran.artbooktesting.util.Resource
import java.lang.Exception
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
) : ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.imageSearch(imageString)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            }else{
                Resource.error("Error",null)
            }
        }catch (e:Exception){
            Resource.error("No data!",null)
        }
    }
}