package lus.areapass.auth.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BaseViewModel
import lus.areapass.entities.User
import lus.areapass.network.ApiService
import lus.areapass.network.Error
import lus.areapass.network.Success
import java.util.*
import javax.inject.Inject


class CreateAccountViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel() {

    val onSubmit: View.OnClickListener = View.OnClickListener { create() }
    val firstName: MutableLiveData<String> = MutableLiveData()
    val lastName: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()
    val promoCode: MutableLiveData<String> = MutableLiveData()
    val usePromoCode: MutableLiveData<Boolean> = MutableLiveData()


    private fun create() {
        if (validateData()) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val response = apiService.createAccount(buildModel())) {
                    is Success -> user.postValue(response.data)
                    is Error -> postError(response.message)
                }
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
        if (password.value.isNullOrBlank()) {
            setError("The password is required")
            return false
        } else if (!Objects.equals(password.value, confirmPassword.value)) {
            setError("Passwords aren't matched")
            return false;
        }
        usePromoCode.value?.let {
            if (it && promoCode.value.isNullOrBlank()) {
                setError("The promo code is required")
                return false
            }
        }
        return true
    }

    private fun buildModel(): User {
        val data = User(email.value, password.value)
        data.firstName = firstName.value
        data.lastName = lastName.value
        data.username = username.value
        data.promoCode = promoCode.value
        return data
    }

}