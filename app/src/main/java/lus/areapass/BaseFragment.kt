package lus.areapass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import lus.areapass.notification.ErrorDialog


abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding> : Fragment() {

    protected abstract val viewModel: VM
    protected lateinit var binding: VB

    protected abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
            .inflate<ViewDataBinding>(inflater, getLayoutId(), container, false)
            .apply {
                lifecycleOwner = this@BaseFragment.viewLifecycleOwner
                setVariable(BR.viewModel, viewModel)
            } as VB
        return binding.root
    }

    protected fun onFailure(errors: List<String>) {
        if (!errors.isNullOrEmpty()) {
            ErrorDialog.show(childFragmentManager, errors.first())
        }
    }

    fun isProcessing(): Boolean {
        return false
    }

}