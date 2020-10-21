package lus.areapass.pass

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lus.areapass.BaseFragment
import lus.areapass.R
import lus.areapass.di.viewModel
import lus.areapass.pass.viewmodels.PassRootViewModel
import lus.areapass.pass.viewmodels.PassViewModel


@AndroidEntryPoint
class CreatePassFragment : BaseFragment<PassViewModel, ViewDataBinding>() {

    private val navi by activityViewModels<PassRootViewModel>()

    override val viewModel by viewModel<PassViewModel> {
        with(it) {
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