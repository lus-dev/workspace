package lus.areapass.utils

import android.view.View
import androidx.databinding.BindingAdapter


object Bindings {

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("background")
    fun setBackground(view: View, resId: Int) {
        if (resId != 0) {
            view.setBackgroundResource(resId)
        }
    }

}
