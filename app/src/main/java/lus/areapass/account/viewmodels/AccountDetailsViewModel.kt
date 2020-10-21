package lus.areapass.account.viewmodels

import android.content.Context
import android.view.View
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BaseViewModel
import lus.areapass.R
import lus.areapass.cache.UserPreferences
import lus.areapass.cache.UserSavedState
import lus.areapass.entities.network.IAccount
import lus.areapass.entities.person.Contact
import lus.areapass.entities.person.IContact
import lus.areapass.network.ApiService
import lus.areapass.network.Error
import lus.areapass.network.Success


class AccountDetailsViewModel @ViewModelInject constructor(
    @ApplicationContext appContext: Context,
    private val apiService: ApiService,
    private val userPreferences: UserPreferences,
    @Assisted private val savedState: SavedStateHandle
) : BaseViewModel(appContext) {

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
        val savedStateUser = UserSavedState(savedState)
        val contact = if (savedStateUser.contains()) savedStateUser.contact() else userPreferences.contact()
        with(this@AccountDetailsViewModel) {
            firstName.value = contact.firstName
            lastName.value = contact.lastName
            username.value = contact.username
            email.value = contact.email
        }
    }

    fun saveState() {
        UserSavedState(savedState).save(contact())
    }

    private fun contact(): IContact {
        return Contact(firstName.value ?: "", lastName.value ?: "", username.value ?: "", email.value ?: "")
    }

    private fun signOut() {
        userPreferences.unauthorize()
        onSignedOut.value = Unit
    }

    private fun saveChanges() {
        if (validate()) {
            userPreferences.save(contact())
            updateAccount(userPreferences.user())
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

    private fun updateAccount(data: IAccount) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = apiService.updateAccount(data)) {
                is Success -> onChangeSaved.postValue(Unit)
                is Error -> postError(response.message)
            }
        }
    }

}