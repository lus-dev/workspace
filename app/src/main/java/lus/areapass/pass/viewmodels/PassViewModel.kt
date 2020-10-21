package lus.areapass.pass.viewmodels

import android.content.Context
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import lus.areapass.BaseViewModel
import lus.areapass.R
import lus.areapass.network.ApiService


class PassViewModel @ViewModelInject constructor(
    @ApplicationContext private val appContext: Context,
    private val apiService: ApiService
) : BaseViewModel(appContext) {

    val areaName: MutableLiveData<String> = MutableLiveData()
    val expireDate: MutableLiveData<String> = MutableLiveData()
    val price: MutableLiveData<String> = MutableLiveData()
    val visits: MutableLiveData<String> = MutableLiveData()
    val onExpireDateSet: MutableLiveData<Unit> = MutableLiveData()
    val onSubmit: View.OnClickListener = View.OnClickListener { create() }

    private fun create() {
        if (validate()) {
//            viewModelScope.launch(Dispatchers.IO) {
//                when (val response = apiService.createAccount(contact, credentials, discount)) {
//                    is Success -> user.postValue(response.data)
//                    is Error -> postError(response.message)
//                }
//            }
        }
    }

    private fun validate(): Boolean {
        return listOf(
            areaName to R.string.error_empty_area_name,
            expireDate to R.string.error_empty_pass_expire_date,
            price to R.string.error_empty_pass_price,
            visits to R.string.error_empty_pass_visits)
            .all { info -> validate(info.first.value, info.second) }
    }

}