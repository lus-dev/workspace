package lus.areapass.account.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AccountViewModel @ViewModelInject constructor() : AccountNavigator, ViewModel() {

    override val title: MutableLiveData<String> = MutableLiveData()
    override val showToolbar: MutableLiveData<Boolean> = MutableLiveData()
    override val showBack: MutableLiveData<Boolean> = MutableLiveData()
    override val onChangePassword: MutableLiveData<Unit> = MutableLiveData()
    override val onShowSubscription: MutableLiveData<Unit> = MutableLiveData()
    override val onSignOut: MutableLiveData<Unit> = MutableLiveData()
    override val onBack: MutableLiveData<Unit> = MutableLiveData()
    override val onRefreshUi: MutableLiveData<Unit> = MutableLiveData()

}