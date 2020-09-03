package lus.areapass.pass.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lus.areapass.cache.UserPreferences
import lus.areapass.entities.network.User
import javax.inject.Inject


class PassRootViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : PassNavigation, ViewModel() {

    override val title: MutableLiveData<String> = MutableLiveData()
    override val showToolbar: MutableLiveData<Boolean> = MutableLiveData()
    override val showBack: MutableLiveData<Boolean> = MutableLiveData()
    override val onCreatePass: MutableLiveData<Unit> = MutableLiveData()
    override val onBack: MutableLiveData<Unit> = MutableLiveData()
    override val onRefreshUi: MutableLiveData<Unit> = MutableLiveData()

}