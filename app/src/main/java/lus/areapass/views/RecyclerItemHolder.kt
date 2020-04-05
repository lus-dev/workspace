package lus.areapass.views

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class RecyclerItemHolder constructor(view: View): RecyclerView.ViewHolder(view) {

    // TODO access modifier
    val binding: ViewDataBinding? = DataBindingUtil.bind(view)

}