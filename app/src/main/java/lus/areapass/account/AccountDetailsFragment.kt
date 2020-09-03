package lus.areapass.account

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.account.viewmodels.AccountDetailsViewModel
import lus.areapass.di.activityViewModel
import lus.areapass.di.injector
import lus.areapass.di.statefulViewModel
import lus.areapass.notification.InfoDialog


class AccountDetailsFragment : BaseFragment<AccountDetailsViewModel, ViewDataBinding>() {

    private val navigation by activityViewModel { injector.accountViewModel }

    override val viewModel by statefulViewModel {
        injector.accountDetailsViewModel
            .create(it)
            .apply {
                onChangePassword.value = View.OnClickListener { navigation.onChangePassword.value = Unit }
                onShowSubscription.value = View.OnClickListener { navigation.onShowSubscription.value = Unit }
                onSignedOut.observe(this@AccountDetailsFragment, Observer { navigation.onSignOut.value = Unit })
                onChangeSaved.observe(this@AccountDetailsFragment, Observer { onChangeSaved() })
                errors.observe(this@AccountDetailsFragment, Observer { onFailure(it) })
            }
    }

    override fun getLayoutId() = R.layout.fragment_account

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation.showToolbar(title = getString(R.string.title_account))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }

    private fun onChangeSaved() {
        InfoDialog.show(childFragmentManager, "Changes successfully saved")
    }

}