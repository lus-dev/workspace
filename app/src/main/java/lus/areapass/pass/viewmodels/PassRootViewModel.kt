package lus.areapass.pass.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lus.areapass.cache.UserPreferences
import lus.areapass.entities.person.User
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

    fun rememberUserLocally(data: User) = userPreferences.save(data)

}