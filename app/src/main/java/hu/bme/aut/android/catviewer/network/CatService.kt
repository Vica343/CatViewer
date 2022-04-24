package hu.bme.aut.android.catviewer.network

import com.skydoves.sandwich.ApiResponse
import hu.bme.aut.android.catviewer.model.Cat

import retrofit2.http.GET

interface CatService {
    @GET("images/search?limit=15")
    suspend fun fetchCatList(): ApiResponse<List<Cat>>
}