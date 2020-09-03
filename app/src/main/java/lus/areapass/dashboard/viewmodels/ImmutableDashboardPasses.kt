package lus.areapass.dashboard.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.dashboard.DashboardNavigator
import lus.areapass.dashboard.DashboardPassesAdapter
import lus.areapass.entities.network.IAccount
import lus.areapass.network.ApiService
import lus.areapass.network.Success
import kotlin.reflect.KProperty

class ImmutableDashboardPasses constructor(
    private val scope: CoroutineScope,
    private val apiService: ApiService,
    private val user: LiveData<IAccount>
) {

    operator fun getValue(navigator: DashboardNavigator, property: KProperty<*>): LiveData<RecyclerView.Adapter<out RecyclerView.ViewHolder>> {
        val passes = MutableLiveData<RecyclerView.Adapter<out RecyclerView.ViewHolder>>()
        user.value?.let {
            scope.launch(Dispatchers.IO) {
                when (val response = apiService.fetchEndingPasses(it.credentials)) {
                    is Success -> {
                        // TODO Provide set data method and apply notify method call
                        val data = DashboardPassesAdapter(response.data)
                        passes.postValue(data)
                    }
//                is Error -> postError(response.message)
                }
            }
        }
        return passes
    }

}