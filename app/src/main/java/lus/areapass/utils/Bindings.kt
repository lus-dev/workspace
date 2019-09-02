package lus.areapass.utils

import android.view.View
import androidx.databinding.BindingAdapter


object Bindings {

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

}
