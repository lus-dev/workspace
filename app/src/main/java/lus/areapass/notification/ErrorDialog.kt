package lus.areapass.notification

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import lus.areapass.R
import lus.areapass.notification.viewmodels.ErrorViewModel


class ErrorDialog constructor(message: String) : BaseDialog<ErrorViewModel, ViewDataBinding>() {

    companion object {
        fun show(manager: FragmentManager, message: String) = ErrorDialog(message).show(manager, null)
    }

    init {
        viewModel = ErrorViewModel(message).apply {
            onClose.value = View.OnClickListener { close() }
        }
    }


    override fun getLayoutId() = R.layout.dialog_error

    override fun provideViewModel() = viewModel

}