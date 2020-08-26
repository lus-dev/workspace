package lus.areapass.notification

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import lus.areapass.BR


abstract class BaseDialog<VM : ViewModel, VB : ViewDataBinding> : DialogFragment() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM


    protected abstract fun getLayoutId(): Int
    protected abstract fun provideViewModel(): VM
    protected abstract fun getStyleId(): Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(activity)
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false)
        binding.setVariable(BR.viewModel, provideViewModel())
        return AlertDialog.Builder(requireActivity(), getStyleId())
            .setView(binding.getRoot())
            .create()
            .apply {
                setCanceledOnTouchOutside(false)
            }
    }

    protected fun close() {
        dialog?.dismiss()
    }

}