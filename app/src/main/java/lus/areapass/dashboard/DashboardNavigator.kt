package lus.areapass.dashboard

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import lus.areapass.Navigator


interface DashboardNavigator : Navigator {

    val onViewAreas: MutableLiveData<Unit>
    val onViewAccount: MutableLiveData<Unit>

    val username: LiveData<String>
    val trial: LiveData<String>
    val about: String
    val passes: LiveData<RecyclerView.Adapter<out RecyclerView.ViewHolder>>

    fun onViewAreasClick(view: View) {onViewAreas.value = Unit}
    fun onViewAccountClick(view: View) {onViewAccount.value = Unit}

}