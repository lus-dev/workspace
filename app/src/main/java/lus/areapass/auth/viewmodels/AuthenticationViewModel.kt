package lus.areapass.auth.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lus.areapass.cache.UserPreferences
import lus.areapass.entities.network.IAccount
import javax.inject.Inject


class AuthenticationViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : AuthenticationNavigator, ViewModel() {

    override val title: MutableLiveData<String> = MutableLiveData()
    override val showToolbar: MutableLiveData<Boolean> = MutableLiveData()
    override val showBack: MutableLiveData<Boolean> = MutableLiveData()
    override val onSignIn: MutableLiveData<IAccount> = MutableLiveData()
    override val onCreateAccount: MutableLiveData<Unit> = MutableLiveData()
    override val onResetPassword: MutableLiveData<Unit> = MutableLiveData()
    override val onBack: MutableLiveData<Unit> = MutableLiveData()
    override val onRefreshUi: MutableLiveData<Unit> = MutableLiveData()

    fun rememberUserLocally(data: IAccount) = userPreferences.save(data)

}