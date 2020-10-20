package lus.areapass.utils

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


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

    @JvmStatic
    @BindingAdapter("adapter")
    fun setRecyclerAdapter(view: RecyclerView?, adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?) {
        // TODO Investigate why here might come smt NULL
        if (adapter == null) return
        view?.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("dateText")
    fun formatDate(view: EditText, value: LocalDate) {
        value.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }

}
