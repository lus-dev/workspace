package lus.areapass.auth

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.auth.viewmodels.AuthenticationNavigator
import lus.areapass.auth.viewmodels.ResetPasswordViewModel
import lus.areapass.di.injector
import lus.areapass.di.viewModel


class ResetPasswordFragment : BaseFragment<ResetPasswordViewModel, ViewDataBinding, AuthenticationNavigator>() {

    override val viewModel by viewModel {
        injector.resetPasswordViewModel.apply {
            onSuccess.observe(this@ResetPasswordFragment, Observer { navigator.onBack.value = Unit })
            errors.observe(this@ResetPasswordFragment, Observer { onFailure(it) })
        }
    }

    override fun getLayoutId() = R.layout.fragment_reset_password

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator.showToolbar("Reset Password")
    }

}