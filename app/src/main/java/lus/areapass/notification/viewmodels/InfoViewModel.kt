package lus.areapass.notification.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class InfoViewModel constructor(val message: String): ViewModel() {

    val onClose: MutableLiveData<View.OnClickListener> = MutableLiveData()

}