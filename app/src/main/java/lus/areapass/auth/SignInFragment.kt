package lus.areapass.auth

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.auth.viewmodels.AuthenticationNavigator
import lus.areapass.auth.viewmodels.SignInViewModel
import lus.areapass.di.activityViewModel
import lus.areapass.di.injector
import lus.areapass.di.viewModel


class SignInFragment : BaseFragment<SignInViewModel, ViewDataBinding>() {

    private val navi by activityViewModel { injector.authenticationViewModel }

    override val viewModel by viewModel {
        injector.signInViewModel.apply {
            onCreateAccount.value = View.OnClickListener { navi.onCreateAccount.value = Unit }
            onResetPassword.value = View.OnClickListener { navi.onResetPassword.value = Unit }
            user.observe(this@SignInFragment, Observer { navi.onSignIn.value = it })
            errors.observe(this@SignInFragment, Observer { onFailure(it) })
        }
    }

    override fun getLayoutId() = R.layout.fragment_sign_in

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navi.hideToolbar()
    }

}