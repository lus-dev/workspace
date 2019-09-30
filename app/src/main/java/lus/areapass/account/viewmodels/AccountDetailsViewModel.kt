package lus.areapass.account.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import lus.areapass.BaseViewModel
import lus.areapass.cache.UserPreferences
import lus.areapass.network.ApiService
import javax.inject.Inject


class AccountDetailsViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : BaseViewModel() {

    val onSignOut: View.OnClickListener = View.OnClickListener { signOut() }
    val firstName: MutableLiveData<String> = MutableLiveData()
    val lastName: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val onChangePassword: MutableLiveData<View.OnClickListener> = MutableLiveData()
    val onSignedOut: MutableLiveData<Unit> = MutableLiveData()

    init {
        val user = userPreferences.load()
        firstName.value = user.firstName
        lastName.value = user.lastName
        email.value = user.email
    }

    private fun signOut() {
        userPreferences.markNonAuthorized()
        onSignedOut.value = Unit
    }

}