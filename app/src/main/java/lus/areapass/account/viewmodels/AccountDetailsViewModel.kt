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

    val onSaveChanges: View.OnClickListener = View.OnClickListener { saveChanges() }
    val onSignOut: View.OnClickListener = View.OnClickListener { signOut() }
    val username: MutableLiveData<String> = MutableLiveData()
    val firstName: MutableLiveData<String> = MutableLiveData()
    val lastName: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val onChangePassword: MutableLiveData<View.OnClickListener> = MutableLiveData()
    val onSignedOut: MutableLiveData<Unit> = MutableLiveData()
    val onChangeSaved: MutableLiveData<Unit> = MutableLiveData()

    init {
        val data = userPreferences.load()
        user.value = data
        username.value = data.username
        firstName.value = data.firstName
        lastName.value = data.lastName
        email.value = data.email
    }

    private fun signOut() {
        userPreferences.markNonAuthorized()
        onSignedOut.value = Unit
    }

    private fun saveChanges() {
        user.value?.let {
            it.username = username.value
            it.firstName = firstName.value
            it.lastName = lastName.value
            it.email = email.value
            userPreferences.save(it)
        }
        onChangeSaved.value = Unit
    }

}