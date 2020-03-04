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
import java.util.*
import javax.inject.Inject


class ChangePasswordViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : BaseViewModel() {

    val onChangeClick: View.OnClickListener = View.OnClickListener { changePassword() }
    val newPassword: MutableLiveData<String> = MutableLiveData()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()
    val onChanged: MutableLiveData<Unit> = MutableLiveData()

    private fun changePassword() {
        if (validate()) {
            userPreferences.load().apply {
                password = newPassword.value
            }.also {
                changePassword(it)
            }
        }
    }

    private fun validate(): Boolean {
        if (newPassword.value.isNullOrBlank()) {
            setError("The new password is required")
            return false
        } else if (!Objects.equals(newPassword.value, confirmPassword.value)) {
            setError("Passwords aren't matched")
            return false;
        }
        return true
    }

    private fun changePassword(data: User) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = apiService.changePassword(data)) {
                is Success -> onChanged.postValue(Unit)
                is Error -> postError(response.message)
            }
        }
    }

}