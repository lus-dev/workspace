package lus.areapass.account.viewmodels

import android.content.Context
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import lus.areapass.BaseViewModel
import lus.areapass.R
import lus.areapass.cache.UserPreferences
import lus.areapass.network.ApiService
import java.util.*


class ChangePasswordViewModel @ViewModelInject constructor(
    @ApplicationContext private val appContext: Context,
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : BaseViewModel(appContext) {

    val onChangeClick: View.OnClickListener = View.OnClickListener { changePassword() }
    val newPassword: MutableLiveData<String> = MutableLiveData()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()
    val onChanged: MutableLiveData<Unit> = MutableLiveData()

    private fun changePassword() {
        if (validate()) {
            // TODO Implement stub
//            viewModelScope.launch(Dispatchers.IO) {
//                when (val response = apiService.changePassword(data)) {
//                    is Success -> onChanged.postValue(Unit)
//                    is Error -> postError(response.message)
//                }
//            }
        }
    }

    private fun validate(): Boolean {
        if (validate(newPassword.value, R.string.error_empty_newpassword)) {
            if (Objects.equals(newPassword.value, confirmPassword.value)) {
                return true
            }
            setError(appContext.getString(R.string.error_not_matched_passwords))
        }
        return false
    }

}