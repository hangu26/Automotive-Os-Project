package kr.baeksuk.ccodeproject

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.car.app.navigation.model.MessageInfo
import androidx.car.app.navigation.model.NavigationTemplate

class MapScreen(
    carContext: CarContext,
    private val destLat: Double,
    private val destLng: Double
) : Screen(carContext) {

    private var infoText = "위치 확인 중..."
    private var isLoaded = false

    override fun onGetTemplate(): Template {

        if (!isLoaded) {
            isLoaded = true

            val locationHelper = LocationHelper(carContext)

            locationHelper.getCurrentLocation { location ->
                if (location != null) {

                    val distance = calculateDistance(
                        location.latitude,
                        location.longitude,
                        destLat,
                        destLng
                    )

                    val time = calculateTime(distance)

                    infoText = "${distance.toInt()}m / 약 ${time}분"
                } else {
                    infoText = "위치 가져오기 실패"
                }

                invalidate()
            }
        }

        val pane = Pane.Builder()
            .addRow(
                Row.Builder()
                    .setTitle("목적지")
                    .addText(infoText)
                    .build()
            )
            .build()

        return PaneTemplate.Builder(pane)
            .setTitle("네비게이션")
            .setHeaderAction(Action.APP_ICON)
            .build()
    }

    private fun calculateDistance(
        startLat: Double,
        startLng: Double,
        endLat: Double,
        endLng: Double
    ): Float {
        val results = FloatArray(1)
        android.location.Location.distanceBetween(
            startLat, startLng,
            endLat, endLng,
            results
        )
        return results[0]
    }

    private fun calculateTime(distanceMeter: Float): Int {
        val speed = 40_000f / 3600f
        return (distanceMeter / speed / 60).toInt()
    }
}