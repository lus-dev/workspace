package lus.areapass.notification

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import lus.areapass.R
import lus.areapass.notification.viewmodels.InfoViewModel


class InfoDialog constructor(message: String): BaseDialog<InfoViewModel, ViewDataBinding>() {

    companion object {
        fun show(manager: FragmentManager, message: String) = InfoDialog(message).show(manager, null)
    }

    init {
        viewModel = InfoViewModel(message).apply {
            onClose.value = View.OnClickListener { close() }
        }
    }


    override fun getLayoutId() = R.layout.dialog_info

    override fun provideViewModel() = viewModel

    override fun getStyleId() = R.style.Dialog_Error

}