package kr.baeksuk.ccodeproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesApi {
    @GET("maps/api/place/textsearch/json")
    fun searchPlace(
        @Query("query") query: String,
        @Query("key") apiKey: String
    ): Call<PlacesResponse>
}