package elfak.mosis.myplaces.model

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import elfak.mosis.myplaces.data.MyPlace

class MyPlacesViewModel: ViewModel() {
    var myPlacesList: ArrayList<MyPlace> = ArrayList<MyPlace>()
    var selected: MyPlace? = null

    fun addPlace(place: MyPlace){
        myPlacesList.add(place)
    }
}
