package kr.baeksuk.ccodeproject

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class ResultScreen(
    carContext: CarContext,
    private val places: List<Place>
) : Screen(carContext) {

    override fun onGetTemplate(): Template {

        val itemList = ItemList.Builder()

        places.forEach { place ->

            val lat = place.geometry.location.lat
            val lng = place.geometry.location.lng

            itemList.addItem(
                Row.Builder()
                    .setTitle(place.name)
                    .setOnClickListener {
                        screenManager.push(
                            MapScreen(carContext, lat, lng)
                        )
                    }
                    .build()
            )
        }

        return ListTemplate.Builder()
            .setSingleList(itemList.build())
            .setTitle("검색 결과")
            .setHeaderAction(Action.BACK)
            .build()
    }
}