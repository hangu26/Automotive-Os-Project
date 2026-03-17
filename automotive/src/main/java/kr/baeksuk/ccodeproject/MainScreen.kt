package kr.baeksuk.ccodeproject

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row

class MainScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): ListTemplate {

        val row = Row.Builder()
            .setTitle("차량용 AVN 앱")
            .addText("GitHub: hangu26/Automotive-Os-Project")
            .build()

        val itemList = ItemList.Builder()
            .addItem(row)
            .build()

        return ListTemplate.Builder()
            .setSingleList(itemList)
            .setTitle("메인 화면")
            .build()
    }
}