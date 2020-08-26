package lus.areapass.account.viewmodels

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BaseViewModel
import lus.areapass.R
import lus.areapass.cache.UserPreferences
import lus.areapass.cache.UserSavedState
import lus.areapass.entities.person.Contact
import lus.areapass.entities.person.IContact
import lus.areapass.entities.person.User
import lus.areapass.network.ApiService
import lus.areapass.network.Error
import lus.areapass.network.Success
import javax.inject.Inject


class AccountDetailsViewModel constructor(
    private val appContext: Context,
    private val apiService: ApiService,
    private val userPreferences: UserPreferences,
    private val savedState: SavedStateHandle
) : BaseViewModel(appContext) {

    class Factory @Inject constructor(
        private val appContext: Context,
        private val apiService: ApiService,
        private val userPreferences: UserPreferences
    ) {
        fun create(state: SavedStateHandle) =
            AccountDetailsViewModel(appContext, apiService, userPreferences, state)
    }

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
        val cachedUser = userPreferences.user()
        val savedStateUser = UserSavedState(savedState)
        val user = if (savedStateUser.contains()) cachedUser.copy(contact = savedStateUser.contact()) else cachedUser

        with(user.contact) {
            this@AccountDetailsViewModel.firstName.value = firstName
            this@AccountDetailsViewModel.lastName.value = lastName
            this@AccountDetailsViewModel.username.value = username
            this@AccountDetailsViewModel.email.value = email
        }
    }

    fun saveState() {
        UserSavedState(savedState).save(contact())
    }

    private fun contact(): IContact {
        return Contact(firstName.value
            ?: "", lastName.value ?: "", username.value ?: "", email.value ?: "")
    }

    private fun signOut() {
        userPreferences.unauthorize()
        onSignedOut.value = Unit
    }

    private fun saveChanges() {
        if (validate()) {
            val user = User(-1, contact())
            userPreferences.save(user)
            updateAccount(user)
        }
    }

    private fun validate(): Boolean {
        return listOf(
            firstName to R.string.error_empty_first_name,
            lastName to R.string.error_empty_last_name,
            username to R.string.error_empty_username,
            email to R.string.error_empty_email
        ).all { info -> validate(info.first.value, info.second) }
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