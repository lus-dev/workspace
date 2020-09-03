package lus.areapass.auth.viewmodels

import androidx.lifecycle.MutableLiveData
import lus.areapass.Navigator
import lus.areapass.entities.network.IAccount


interface AuthenticationNavigator : Navigator {
    val onSignIn: MutableLiveData<IAccount>
    val onCreateAccount: MutableLiveData<Unit>
    val onResetPassword: MutableLiveData<Unit>
}