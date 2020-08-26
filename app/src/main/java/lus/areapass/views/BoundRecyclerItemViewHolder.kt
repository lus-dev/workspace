package lus.areapass.views

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class BoundRecyclerItemViewHolder constructor(view: View): RecyclerView.ViewHolder(view) {

    val binding: ViewDataBinding? = DataBindingUtil.bind(view)

}