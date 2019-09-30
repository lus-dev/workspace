package lus.areapass.views

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder


class RecyclerItemHolder constructor(
    view: View,
    adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
) : FlexibleViewHolder(view, adapter) {

    val binding: ViewDataBinding? = DataBindingUtil.bind(view)

}