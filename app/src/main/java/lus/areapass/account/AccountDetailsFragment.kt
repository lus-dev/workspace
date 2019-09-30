package lus.areapass.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.account.viewmodels.AccountDetailsViewModel
import lus.areapass.account.viewmodels.AccountNavigator
import lus.areapass.di.injector
import lus.areapass.di.viewModel


class AccountDetailsFragment: BaseFragment<AccountDetailsViewModel, ViewDataBinding, AccountNavigator>() {

    override val viewModel by viewModel {
        injector.accountDetailsViewModel.apply {
            onChangePassword.value = View.OnClickListener { navigator.onChangePassword.value = Unit }
            onSignedOut.observe(this@AccountDetailsFragment, Observer { navigator.onSignOut.value = Unit })
        }
    }

    override fun getLayoutId() = R.layout.fragment_account

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator.showToolbar(title = getString(R.string.title_account), showBack = true)
    }

}