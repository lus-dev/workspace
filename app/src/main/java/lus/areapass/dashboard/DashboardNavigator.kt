package lus.areapass.dashboard

import android.view.View
import androidx.lifecycle.MutableLiveData
import lus.areapass.Navigator


interface DashboardNavigator : Navigator {

    val onViewAreas: MutableLiveData<Unit>
    val onViewAccount: MutableLiveData<Unit>

    fun onViewAreasClick(view: View) {onViewAreas.value = Unit}
    fun onViewAccountClick(view: View) {onViewAccount.value = Unit}

}