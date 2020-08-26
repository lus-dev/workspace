package lus.areapass.pass.viewmodels

import androidx.lifecycle.MutableLiveData
import lus.areapass.Navigator


interface PassNavigation : Navigator {

    val onCreatePass: MutableLiveData<Unit>

}