package kr.baeksuk.ccodeproject

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.SearchTemplate
import androidx.car.app.model.Template
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {

        return SearchTemplate.Builder(object : SearchTemplate.SearchCallback {

            override fun onSearchTextChanged(searchText: String) {
                // 입력 중 로직
            }

            override fun onSearchSubmitted(searchText: String) {
                val apiKey = carContext.getString(R.string.maps_api_key)

                RetrofitClient.create().searchPlace(searchText, apiKey)
                    .enqueue(object : Callback<PlacesResponse> {
                        override fun onResponse(
                            call: Call<PlacesResponse>,
                            response: Response<PlacesResponse>
                        ) {
                            val results = response.body()?.results ?: emptyList()
                            screenManager.push(ResultScreen(carContext, results))
                        }

                        override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                            Log.e("PlacesAPI", "검색 실패", t)
                        }
                    })
            }
        })
            .setHeaderAction(Action.BACK)
            .build()
    }
}