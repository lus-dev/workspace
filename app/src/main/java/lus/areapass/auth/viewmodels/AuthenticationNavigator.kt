package lus.areapass.auth.viewmodels

import androidx.lifecycle.MutableLiveData
import lus.areapass.Navigator
import lus.areapass.entities.User


interface AuthenticationNavigator : Navigator {
    val onSignIn: MutableLiveData<User>
    val onCreateAccount: MutableLiveData<Unit>
    val onResetPassword: MutableLiveData<Unit>
}