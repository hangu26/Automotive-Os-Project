package kr.baeksuk.ccodeproject

import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices

/** 현재 위치를 비동기로 가져와서 전달하는 콜백 함수 **/
class LocationHelper(private val context : Context) {

    /** 가장 적절한 위치를 계산해주는 API **/
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    /** 위치를 받아오면 제공하는 함수 **/
    fun getCurrentLocation(onResult : (Location?) -> Unit) {
        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    Log.d("위치", "위치 가져오기 성공: ${location?.latitude}, ${location?.longitude}")
                    onResult(location)
                }
                .addOnFailureListener {
                    Log.d("위치", "위치 가져오기 실패: ${it.message}")
                    onResult(null)
                }
        } catch (e: SecurityException) {
            // 위치 권한이 없는 경우 예외 처리
            onResult(null)
            Log.d("위치", "위치 권한이 없습니다: ${e.message}")
        }
    }

}