package lus.areapass.account

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.account.viewmodels.AccountDetailsViewModel
import lus.areapass.account.viewmodels.AccountNavigator
import lus.areapass.di.injector
import lus.areapass.di.viewModel
import lus.areapass.notification.ErrorDialog


class AccountDetailsFragment: BaseFragment<AccountDetailsViewModel, ViewDataBinding, AccountNavigator>() {

    override val viewModel by viewModel {
        injector.accountDetailsViewModel.apply {
            onChangePassword.value = View.OnClickListener { navigator.onChangePassword.value = Unit }
            onSignedOut.observe(this@AccountDetailsFragment, Observer { navigator.onSignOut.value = Unit })
            onChangeSaved.observe(this@AccountDetailsFragment, Observer { onChangeSaved() })
        }
    }

    override fun getLayoutId() = R.layout.fragment_account

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator.showToolbar(title = getString(R.string.title_account))
    }

    private fun onChangeSaved() {
        // TODO Make service call. Add information dialog
        ErrorDialog.show(childFragmentManager, "Changes successfully saved")
    }

}