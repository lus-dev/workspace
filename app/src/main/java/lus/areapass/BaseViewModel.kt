package lus.areapass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel(private val appContext: Context): ViewModel() {

    val errors: MutableLiveData<List<String>> = MutableLiveData()

    init {
        errors.value = ArrayList()
    }

    protected fun setError(message: String) {
        errors.value = arrayListOf(message)
    }

    protected fun postError(message: String) {
        errors.postValue(arrayListOf(message))
    }

    protected fun validate(value: String?, errorResId: Int): Boolean {
        if (value.isNullOrBlank()) {
            setError(appContext.getString(errorResId))
            return false
        }
        return true
    }

}