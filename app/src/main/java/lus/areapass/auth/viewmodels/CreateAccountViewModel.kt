package lus.areapass.auth.viewmodels

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BaseViewModel
import lus.areapass.R
import lus.areapass.entities.credentials.EmailCredentials
import lus.areapass.entities.discount.IDiscount
import lus.areapass.entities.discount.Promocode
import lus.areapass.entities.discount.ZeroDiscount
import lus.areapass.entities.network.IAccount
import lus.areapass.entities.person.Contact
import lus.areapass.network.ApiService
import lus.areapass.network.Error
import lus.areapass.network.Success
import java.util.*
import javax.inject.Inject


class CreateAccountViewModel @Inject constructor(
    private val appContext: Context,
    private val apiService: ApiService
) : BaseViewModel(appContext) {

    val user: MutableLiveData<IAccount> = MutableLiveData()
    val onSubmit: View.OnClickListener = View.OnClickListener { create() }
    val firstName: MutableLiveData<String> = MutableLiveData()
    val lastName: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()
    val promoCode: MutableLiveData<String> = MutableLiveData()
    val usePromoCode: MutableLiveData<Boolean> = MutableLiveData()

    private val contact
        get() = Contact(
            firstName.value ?: "",
            lastName.value ?: "",
            username.value ?: "",
            email.value ?: "")
    private val credentials
        get() = EmailCredentials(
            email.value ?: "",
            password.value ?: "")
    private val discount: IDiscount
        get() {
            val code = promoCode.value
            return if (code != null && code.isNotBlank()) Promocode(code) else ZeroDiscount()
        }

    private fun create() {
        if (validate()) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val response = apiService.createAccount(contact, credentials, discount)) {
                    is Success -> user.postValue(response.data)
                    is Error -> postError(response.message)
                }
            }
        }
    }

    // TODO Add email format validation
    // Patterns.EMAIL_ADDRESS.matcher(email)
    private fun validate(): Boolean {
        val fieldsFilled = listOf(
            firstName to R.string.error_empty_first_name,
            lastName to R.string.error_empty_last_name,
            email to R.string.error_empty_email,
            username to R.string.error_empty_username,
            password to R.string.error_empty_password)
            .all { info -> validate(info.first.value, info.second) }

        if (fieldsFilled) {
            if (!Objects.equals(password.value, confirmPassword.value)) {
                setError(appContext.getString(R.string.error_not_matched_passwords))
                return false;
            }
        } else {
            return false
        }

        usePromoCode.value?.let {
            if (it && promoCode.value.isNullOrBlank()) {
                setError(appContext.getString(R.string.error_empty_promocode))
                return false
            }
        }
        return true
    }

}