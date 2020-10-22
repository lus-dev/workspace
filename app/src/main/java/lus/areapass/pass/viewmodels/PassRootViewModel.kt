package lus.areapass.pass.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lus.areapass.cache.UserPreferences


class PassRootViewModel @ViewModelInject constructor(
    private val userPreferences: UserPreferences
) : PassNavigation, ViewModel() {

    override val title: MutableLiveData<String> = MutableLiveData()
    override val showToolbar: MutableLiveData<Boolean> = MutableLiveData()
    override val showBack: MutableLiveData<Boolean> = MutableLiveData()
    override val onCreatePass: MutableLiveData<Unit> = MutableLiveData()
    override val onBack: MutableLiveData<Unit> = MutableLiveData()
    override val onRefreshUi: MutableLiveData<Unit> = MutableLiveData()

}