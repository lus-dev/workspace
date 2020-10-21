package lus.areapass.account

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.account.viewmodels.AccountDetailsViewModel
import lus.areapass.account.viewmodels.AccountViewModel
import lus.areapass.di.viewModel
import lus.areapass.notification.InfoDialog


@AndroidEntryPoint
class AccountDetailsFragment : BaseFragment<AccountDetailsViewModel, ViewDataBinding>() {

    private val navigation by activityViewModels<AccountViewModel>()

    override val viewModel by viewModel<AccountDetailsViewModel> {
        with(it) {
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