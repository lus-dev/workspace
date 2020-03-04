package lus.areapass.account.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BaseViewModel
import lus.areapass.cache.UserPreferences
import lus.areapass.entities.User
import lus.areapass.network.ApiService
import lus.areapass.network.Error
import lus.areapass.network.Success
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
    val onShowSubscription: MutableLiveData<View.OnClickListener> = MutableLiveData()
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
        if (validateData()) {
            user.value?.apply {
                username = this@AccountDetailsViewModel.username.value
                firstName = this@AccountDetailsViewModel.firstName.value
                lastName = this@AccountDetailsViewModel.lastName.value
                email = this@AccountDetailsViewModel.email.value
            }?.also {
                userPreferences.save(it)
                updateAccount(it)
            }
        }
    }

    private fun validateData(): Boolean {
        if (firstName.value.isNullOrBlank()) {
            setError("The first name is required")
            return false
        }
        if (lastName.value.isNullOrBlank()) {
            setError("The last name is required")
            return false
        }
        if (email.value.isNullOrBlank()) {
            setError("The email is required")
            return false
        }
        if (username.value.isNullOrBlank()) {
            setError("The username is required")
            return false
        }
        return true
    }

    private fun updateAccount(data: User) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = apiService.updateAccount(data)) {
                is Success -> onChangeSaved.postValue(Unit)
                is Error -> postError(response.message)
            }
        }
    }

}