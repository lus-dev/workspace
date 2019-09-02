package lus.areapass.auth

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.auth.viewmodels.AuthenticationNavigator
import lus.areapass.auth.viewmodels.SignInViewModel
import lus.areapass.di.injector
import lus.areapass.di.viewModel


class SignInFragment : BaseFragment<SignInViewModel, ViewDataBinding, AuthenticationNavigator>() {

    override val viewModel by viewModel {
        injector.signInViewModel.apply {
            onCreateAccount.value = View.OnClickListener { navigator.onCreateAccount.value = Unit }
            onResetPassword.value = View.OnClickListener { navigator.onResetPassword.value = Unit }
            user.observe(this@SignInFragment, Observer { navigator.onSignIn.value = it })
            errors.observe(this@SignInFragment, Observer { onFailure(it) })
        }
    }

    override fun getLayoutId() = R.layout.fragment_sign_in

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator.hideToolbar()
    }
}