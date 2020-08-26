package lus.areapass.dashboard

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import lus.areapass.Navigator


interface DashboardNavigator : Navigator {

    val onViewAreas: MutableLiveData<Unit>
    val onViewAccount: MutableLiveData<Unit>

    val username: MutableLiveData<String>
    val trial: MutableLiveData<String>
    val about: MutableLiveData<String>
    val passes: MutableLiveData<RecyclerView.Adapter<out RecyclerView.ViewHolder>>

    fun onViewAreasClick(view: View) {onViewAreas.value = Unit}
    fun onViewAccountClick(view: View) {onViewAccount.value = Unit}

}