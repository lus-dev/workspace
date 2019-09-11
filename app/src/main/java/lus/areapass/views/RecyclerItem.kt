package lus.areapass.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import lus.areapass.BR


abstract class RecyclerItem : AbstractFlexibleItem<RecyclerItemHolder>() {

    protected enum class Position {
        FIRST, LAST, DEFAULT, SINGLE
    }


    protected abstract fun onBind(holder: RecyclerItemHolder, position: Position)

    override fun createViewHolder(view: View, adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>): RecyclerItemHolder {
        return RecyclerItemHolder(view, adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?, holder: RecyclerItemHolder, position: Int, payloads: MutableList<Any>?) {
        holder.binding?.setVariable(BR.viewModel, this)
        onBind(holder, definePosition(adapter, position))
    }

    private fun definePosition(adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?, position: Int): Position {
        if (adapter?.itemCount == 1) {
            return Position.SINGLE
        }
        return when (position) {
            0 -> Position.FIRST
            (adapter?.itemCount?.minus(1)) -> Position.LAST
            else -> Position.DEFAULT
        }
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

}