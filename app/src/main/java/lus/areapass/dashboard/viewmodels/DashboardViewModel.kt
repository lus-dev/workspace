package lus.areapass.dashboard.viewmodels

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dagger.hilt.android.qualifiers.ApplicationContext
import lus.areapass.BuildConfig
import lus.areapass.R
import lus.areapass.cache.UserPreferences
import lus.areapass.dashboard.DashboardNavigator
import lus.areapass.entities.network.IAccount
import lus.areapass.network.ApiService


class DashboardViewModel @ViewModelInject constructor( // @AssistedInject
    @ApplicationContext private val appContext: Context,
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
    override val about: String
        get() {
            return String.format(appContext.getString(R.string.label_dashboard_about), BuildConfig.VERSION_NAME)
        }

    private val user: LiveData<IAccount> by lazy {
        MutableLiveData(userPreferences.user())
    }
    override val username = Transformations.map(user) {
        String.format(appContext.getString(R.string.label_dashboard_username), it.contact.firstName, it.contact.lastName)
    }
    override val trial = Transformations.map(user) {
        String.format(appContext.getString(R.string.label_dashboard_trial), "30 days") // TODO Implement trials
    }
    override val passes by ImmutableDashboardPasses(viewModelScope, apiService, user)

    fun authorized() = userPreferences.authorized()

}