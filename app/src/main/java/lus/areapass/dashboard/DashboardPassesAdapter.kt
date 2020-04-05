package lus.areapass.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lus.areapass.BR
import lus.areapass.R
import lus.areapass.entities.Pass
import lus.areapass.views.RecyclerItemHolder


class DashboardPassesAdapter(private val data: List<Pass>) : RecyclerView.Adapter<RecyclerItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_dashboard_pass, parent, false);
        return RecyclerItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerItemHolder, position: Int) {
        holder.binding?.setVariable(BR.viewModel, DashboardPassItemViewModel(data, position))
    }

    override fun getItemCount() = data.size

}