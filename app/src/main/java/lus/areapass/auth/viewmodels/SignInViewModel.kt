package lus.areapass.auth.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BaseViewModel
import lus.areapass.network.ApiService
import lus.areapass.network.Error
import lus.areapass.network.Success
import javax.inject.Inject


class SignInViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel() {

    val onSignIn: View.OnClickListener = View.OnClickListener { signIn() }
    val identifier: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val onCreateAccount: MutableLiveData<View.OnClickListener> = MutableLiveData()
    val onResetPassword: MutableLiveData<View.OnClickListener> = MutableLiveData()

    private fun signIn() {
        if (validateCredentials()) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val response = apiService.signIn(identifier.value as String, password.value as String)) {
                    is Success -> user.postValue(response.data)
                    is Error -> postError(response.message)
                }
            }
        }
    }

    private fun validateCredentials(): Boolean {
        if (identifier.value.isNullOrBlank()) {
            setError("The email or username are required")
            return false
        }
        if (password.value.isNullOrBlank()) {
            setError("The password is required")
            return false
        }
        return true
    }

}