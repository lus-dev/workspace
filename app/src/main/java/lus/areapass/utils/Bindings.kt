package lus.areapass.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


//object Bindings {

//    @JvmStatic
    @BindingAdapter("visible")
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

//    @JvmStatic
    @BindingAdapter("background")
    fun setBackground(view: View, resId: Int) {
        if (resId != 0) {
            view.setBackgroundResource(resId)
        }
    }

//    @JvmStatic
    @BindingAdapter("adapter")
    fun setRecyclerAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>) {
        view.adapter = adapter
    }

//}
