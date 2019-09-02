package lus.areapass.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lus.areapass.cache.UserPreferences
import lus.areapass.network.ApiService
import javax.inject.Inject


class DashboardViewModel @Inject constructor( // @AssistedInject
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
//    @Assisted val user: User
) : DashboardNavigator, ViewModel() {

//    @AssistedInject.Factory
//    interface Factory {
//        fun create(user: User): DashboardViewModel
//    }

    override val onBack: MutableLiveData<Unit> = MutableLiveData()
    override val onRefreshUi: MutableLiveData<Unit> = MutableLiveData()
    override val title: MutableLiveData<String> = MutableLiveData()
    override val showToolbar: MutableLiveData<Boolean> = MutableLiveData()

    fun getRememberedUser() = userPreferences.load()

}