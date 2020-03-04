package lus.areapass.account.viewmodels

import androidx.lifecycle.MutableLiveData
import lus.areapass.Navigator


interface AccountNavigator : Navigator {
    val onSignOut: MutableLiveData<Unit>
    val onChangePassword: MutableLiveData<Unit>
    val onShowSubscription: MutableLiveData<Unit>
}