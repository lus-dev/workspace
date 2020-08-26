package lus.areapass.dashboard.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BuildConfig
import lus.areapass.R
import lus.areapass.cache.UserPreferences
import lus.areapass.dashboard.DashboardNavigator
import lus.areapass.dashboard.DashboardPassesAdapter
import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.credentials.IdCredentials
import lus.areapass.entities.person.User
import lus.areapass.network.ApiService
import lus.areapass.network.Success
import java.util.*
import javax.inject.Inject


class DashboardViewModel @Inject constructor( // @AssistedInject
    private val appContext: Context,
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
//    @Assisted val user: User
) : DashboardNavigator, ViewModel() {

//    @AssistedInject.Factory
//    interface Factory {
//        fun create(user: User): DashboardViewModel
//    }

    override val onBack: MutableLiveData<Unit> = MutableLiveData()
    override val onViewAreas: MutableLiveData<Unit> = MutableLiveData()
    override val onViewAccount: MutableLiveData<Unit> = MutableLiveData()
    override val onRefreshUi: MutableLiveData<Unit> = MutableLiveData()

    override val title: MutableLiveData<String> = MutableLiveData()
    override val username: MutableLiveData<String> = MutableLiveData()
    override val trial: MutableLiveData<String> = MutableLiveData()
    override val about: MutableLiveData<String> = MutableLiveData()

    override val showToolbar: MutableLiveData<Boolean> = MutableLiveData()
    override val showBack: MutableLiveData<Boolean> = MutableLiveData()

    override val passes: MutableLiveData<RecyclerView.Adapter<out RecyclerView.ViewHolder>> = MutableLiveData()

    init {
        passes.value = DashboardPassesAdapter(Collections.emptyList())
    }

    fun authorized() = userPreferences.authorized()

    fun refreshPasses(credentials: ICredentials) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = apiService.fetchEndingPasses(credentials)) {
                is Success -> {
                    // TODO Provide set data method and apply notify method call
                    val data = DashboardPassesAdapter(response.data)
                    passes.postValue(data)
                }
//                is Error -> postError(response.message)
            }
        }
    }

    fun populateUI() {
        val user = userPreferences.user()
        refreshLabels(user)
        refreshPasses(IdCredentials(user.id))
    }

    private fun refreshLabels(user: User) {
        username.value = String.format(appContext.getString(R.string.label_dashboard_username), user.fullName())
        trial.value = String.format(appContext.getString(R.string.label_dashboard_trial), "30 days") // TODO Implement trials
        about.value = String.format(appContext.getString(R.string.label_dashboard_about), BuildConfig.VERSION_NAME)
    }

}