package lus.areapass.auth

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.auth.viewmodels.AuthenticationNavigator
import lus.areapass.auth.viewmodels.CreateAccountViewModel
import lus.areapass.di.injector
import lus.areapass.di.viewModel


class CreateAccountFragment : BaseFragment<CreateAccountViewModel, ViewDataBinding, AuthenticationNavigator>() {

    override val viewModel by viewModel {
        injector.createAccountViewModel.apply {
            user.observe(this@CreateAccountFragment, Observer { navigator.onSignIn.value = it })
            errors.observe(this@CreateAccountFragment, Observer { onFailure(it) })
            usePromoCode.observe(this@CreateAccountFragment, Observer { binding.invalidateAll() })
        }
    }

    override fun getLayoutId() = R.layout.fragment_create_account

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator.showToolbar("Create Account")
    }
}