package lus.areapass.auth

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.auth.viewmodels.CreateAccountViewModel
import lus.areapass.di.activityViewModel
import lus.areapass.di.injector
import lus.areapass.di.viewModel


class CreateAccountFragment : BaseFragment<CreateAccountViewModel, ViewDataBinding>() {

    private val navigation by activityViewModel { injector.authenticationViewModel }

    override val viewModel by viewModel {
        injector.createAccountViewModel.apply {
            user.observe(this@CreateAccountFragment, Observer { navigation.onSignIn.value = it })
            errors.observe(this@CreateAccountFragment, Observer { onFailure(it) })
            usePromoCode.observe(this@CreateAccountFragment, Observer { binding.invalidateAll() })
        }
    }

    override fun getLayoutId() = R.layout.fragment_create_account

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation.showToolbar(title = "Create Account", showBack = true)
    }

}