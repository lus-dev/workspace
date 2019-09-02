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


class ResetPasswordViewModel @Inject constructor(
    private val apiService: ApiService
): BaseViewModel() {

    val onReset: View.OnClickListener = View.OnClickListener { reset() }
    val identifier: MutableLiveData<String> = MutableLiveData()
    val onSuccess: MutableLiveData<Unit> = MutableLiveData()


    private fun reset() {
        if (validateIdentifier()) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val response = apiService.resetPassword(identifier.value as String)) {
                    is Success -> onSuccess.postValue(Unit)
                    is Error -> postError(response.message)
                }
            }
        }
    }

    private fun validateIdentifier(): Boolean {
        if (identifier.value.isNullOrBlank()) {
            setError("The email or username are required")
            return false
        }
        return true
    }
}