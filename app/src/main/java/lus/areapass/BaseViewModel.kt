package lus.areapass

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lus.areapass.entities.User


abstract class BaseViewModel: ViewModel() {

    val user: MutableLiveData<User> = MutableLiveData()
    val errors: MutableLiveData<MutableList<String>> = MutableLiveData()

    init {
        errors.value = ArrayList()
    }

    protected fun setError(message: String) {
        errors.value = arrayListOf(message)
    }

    protected fun postError(message: String) {
        errors.postValue(arrayListOf(message))
    }

    protected fun clearErrors() {
        errors.value?.clear()
    }

}