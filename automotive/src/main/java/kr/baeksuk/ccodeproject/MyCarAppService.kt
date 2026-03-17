package kr.baeksuk.ccodeproject

import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

class MyCarAppService : CarAppService() {

    /** 호스트 검증기는 앱이 자동차 시스템과 통신할 때 신뢰할 수 있는 호스트만 허용하도록 설정
    여기서는 실습을 위해 모든 호스트를 허용하는 검증기를 반환하지만, 실제 앱에서는 보안을 위해 특정 호스트만 허용하도록 구현해야 함.
     */

    override fun createHostValidator(): HostValidator {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    }

    override fun onCreateSession(): Session {
        return object : Session() {
            override fun onCreateScreen(intent: android.content.Intent) =
                MainScreen(carContext)
        }
    }
}