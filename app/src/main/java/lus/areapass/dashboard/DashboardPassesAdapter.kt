package lus.areapass.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lus.areapass.BR
import lus.areapass.R
import lus.areapass.dashboard.viewmodels.DashboardPassItemViewModel
import lus.areapass.entities.Pass
import lus.areapass.views.BoundRecyclerItemViewHolder


class DashboardPassesAdapter(private val data: List<Pass>) : RecyclerView.Adapter<BoundRecyclerItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoundRecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_dashboard_pass, parent, false);
        return BoundRecyclerItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoundRecyclerItemViewHolder, position: Int) {
        holder.binding?.setVariable(BR.viewModel, DashboardPassItemViewModel(data, position))
    }

    override fun getItemCount() = data.size

}