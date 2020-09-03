package lus.areapass.dashboard.viewmodels

import android.content.Context
import androidx.lifecycle.*
import androidx.lifecycle.Observer
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
    override val showToolbar: MutableLiveData<Boolean> = MutableLiveData()
    override val showBack: MutableLiveData<Boolean> = MutableLiveData()
    override val title: MutableLiveData<String> = MutableLiveData()

    private val user: LiveData<User> = MutableLiveData(userPreferences.user())
    override val username: LiveData<String> = Transformations.map(user) {
        String.format(appContext.getString(R.string.label_dashboard_username), it.fullName())
    }
    override val trial: LiveData<String> = Transformations.map(user) {
        String.format(appContext.getString(R.string.label_dashboard_trial), "30 days") // TODO Implement trials
    }
    override val about: String
        get() {
            return String.format(appContext.getString(R.string.label_dashboard_about), BuildConfig.VERSION_NAME)
        }

    override val passes by ImutableDashboardPasses(viewModelScope, apiService, user)

    fun authorized() = userPreferences.authorized()

}