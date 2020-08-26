package lus.areapass.pass

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
import lus.areapass.pass.viewmodels.PassViewModel


class CreatePassFragment : BaseFragment<PassViewModel, ViewDataBinding>() {

    private val navi by activityViewModel { injector.authenticationViewModel }

    override val viewModel by viewModel {
        injector.createPassViewModel.apply {
            errors.observe(this@CreatePassFragment, Observer { onFailure(it) })
            onExpireDateSet.observe(this@CreatePassFragment, Observer { onExpireDateSet() })
        }
    }

    override fun getLayoutId() = R.layout.fragment_create_account

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navi.showToolbar(title = "Create Account", showBack = true)
    }

    private fun onExpireDateSet() {
        DatePickerFragment(observable = viewModel.expireDate).show(childFragmentManager)
    }

}