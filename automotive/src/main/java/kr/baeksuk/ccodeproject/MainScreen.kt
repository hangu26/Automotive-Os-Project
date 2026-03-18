package kr.baeksuk.ccodeproject

import android.util.Log
import androidx.car.app.AppManager
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.SurfaceCallback
import androidx.car.app.SurfaceContainer
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row

class MainScreen(carContext: CarContext) : Screen(carContext) {

    init {
        // SurfaceCallback 등록
        carContext.getCarService(AppManager::class.java)
            .setSurfaceCallback(object : SurfaceCallback {
                override fun onSurfaceAvailable(surfaceContainer: SurfaceContainer) {
                    // 여기서 구글 맵이나 네이버 맵 등 실제 지도 라이브러리를
                    // surfaceContainer.surface에 렌더링하는 로직이 들어갑니다.
                    Log.d("위치", "Surface 사용 가능!")
                }

                override fun onSurfaceDestroyed(surfaceContainer: SurfaceContainer) {
                    Log.d("위치", "Surface 파괴됨")
                }
            })
    }

    override fun onGetTemplate(): ListTemplate {

        val row = Row.Builder()
            .setTitle("목적지 검색")
            .setOnClickListener {
                screenManager.push(SearchScreen(carContext))
            }
            .build()

        val locationHelper = LocationHelper(carContext)

        locationHelper.getCurrentLocation { location ->
            location?.let {
                Log.d("위치", "위도: ${it.latitude}, 경도: ${it.longitude}")
            }
        }

        val itemList = ItemList.Builder()
            .addItem(row)
            .build()

        return ListTemplate.Builder()
            .setSingleList(itemList)
            .setTitle("메인 화면")
            .build()
    }
}