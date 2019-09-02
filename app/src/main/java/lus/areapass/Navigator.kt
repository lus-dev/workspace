package lus.areapass

import android.view.View
import androidx.lifecycle.MutableLiveData


interface Navigator {
    val title: MutableLiveData<String>
    val showToolbar: MutableLiveData<Boolean>
    val onBack: MutableLiveData<Unit>
    val onRefreshUi: MutableLiveData<Unit>

    fun onBackClick(view: View) {onBack.value = Unit}

    fun showToolbar(title: String) {
        this.title.value = title
        showToolbar.value = true
        onRefreshUi.value = Unit
    }

    fun hideToolbar() {
        title.value = null
        showToolbar.value = false
        onRefreshUi.value = Unit
    }
}