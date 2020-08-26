package lus.areapass.auth.viewmodels

import android.content.Context
import android.util.Patterns
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BaseViewModel
import lus.areapass.R
import lus.areapass.entities.credentials.EmailCredentials
import lus.areapass.entities.credentials.ICredentials
import lus.areapass.entities.person.User
import lus.areapass.entities.credentials.UsernameCredentials
import lus.areapass.network.ApiService
import lus.areapass.network.Error
import lus.areapass.network.Success
import javax.inject.Inject


class SignInViewModel @Inject constructor(
    private val appContext: Context,
    private val apiService: ApiService
) : BaseViewModel(appContext) {

    val user: MutableLiveData<User> = MutableLiveData()
    val onSignIn: View.OnClickListener = View.OnClickListener { signIn() }
    val identifier: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val onCreateAccount: MutableLiveData<View.OnClickListener> = MutableLiveData()
    val onResetPassword: MutableLiveData<View.OnClickListener> = MutableLiveData()

    private val credentials: ICredentials
        get() {
            val matchedEmail = Patterns.EMAIL_ADDRESS.matcher(identifier.value).matches()
            return if (matchedEmail) EmailCredentials(identifier.value!!, password.value!!)
            else UsernameCredentials(identifier.value!!, password.value!!)
        }

    private fun signIn() {
        if (validate()) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val response = apiService.signIn(credentials)) {
                    is Success -> user.postValue(response.data)
                    is Error -> postError(response.message)
                }
            }
        }
    }

    private fun validate(): Boolean {
        return listOf(
            identifier to R.string.error_empty_identifier,
            password to R.string.error_empty_password)
            .all { info -> validate(info.first.value, info.second) }
    }

}