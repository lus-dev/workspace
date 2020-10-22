package lus.areapass.auth

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.auth.viewmodels.AuthenticationViewModel
import lus.areapass.auth.viewmodels.CreateAccountViewModel
import lus.areapass.di.viewModel


@AndroidEntryPoint
class CreateAccountFragment : BaseFragment<CreateAccountViewModel, ViewDataBinding>() {

    private val navigation by activityViewModels<AuthenticationViewModel>()

    override val viewModel by viewModel<CreateAccountViewModel> {
        with(it) {
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