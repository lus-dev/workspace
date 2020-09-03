package lus.areapass.auth

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.auth.viewmodels.ResetPasswordViewModel
import lus.areapass.di.activityViewModel
import lus.areapass.di.injector
import lus.areapass.di.viewModel


class ResetPasswordFragment : BaseFragment<ResetPasswordViewModel, ViewDataBinding>() {

    private val navigation by activityViewModel { injector.authenticationViewModel }

    override val viewModel by viewModel {
        injector.resetPasswordViewModel.apply {
            onSuccess.observe(this@ResetPasswordFragment, Observer { navigation.onBack.value = Unit })
            errors.observe(this@ResetPasswordFragment, Observer { onFailure(it) })
        }
    }

    override fun getLayoutId() = R.layout.fragment_reset_password

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation.showToolbar(title = "Reset Password", showBack = true)
    }

}