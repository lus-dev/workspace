package lus.areapass.auth.viewmodels

import android.content.Context
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lus.areapass.BaseViewModel
import lus.areapass.R
import lus.areapass.network.ApiService
import lus.areapass.network.Error
import lus.areapass.network.Success


class ResetPasswordViewModel @ViewModelInject constructor(
    @ApplicationContext private val appContext: Context,
    private val apiService: ApiService
): BaseViewModel(appContext) {

    val onReset: View.OnClickListener = View.OnClickListener { reset() }
    val identifier: MutableLiveData<String> = MutableLiveData()
    val onSuccess: MutableLiveData<Unit> = MutableLiveData()


    private fun reset() {
        if (validate(identifier.value, R.string.error_empty_identifier)) {
            viewModelScope.launch(Dispatchers.IO) {
                when (val response = apiService.resetPassword(identifier.value as String)) {
                    is Success -> onSuccess.postValue(Unit)
                    is Error -> postError(response.message)
                }
            }
        }
    }

}