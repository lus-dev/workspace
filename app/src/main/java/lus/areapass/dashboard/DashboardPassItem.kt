package lus.areapass.dashboard

import androidx.lifecycle.MutableLiveData
import lus.areapass.R
import lus.areapass.entities.Pass
import lus.areapass.utils.TimeUtil
import lus.areapass.views.RecyclerItem
import lus.areapass.views.RecyclerItemHolder
import org.threeten.bp.Period
import java.text.SimpleDateFormat


class DashboardPassItem constructor(
    private val data: Pass
) : RecyclerItem() {

    val background: MutableLiveData<Int> = MutableLiveData()
    val expirationDate: MutableLiveData<String> = MutableLiveData()
    val visits: MutableLiveData<String> = MutableLiveData()


    override fun getLayoutRes() = R.layout.layout_item_dashboard_pass

    override fun onBind(holder: RecyclerItemHolder, position: Position) {
        background.value = defineBackground(position)
        data.expirationDate?.let {
            expirationDate.value = defineExpirationDate(holder, it)
        }
        visits.value = defineVisits(holder)
    }

    fun getAreaName() = data.areaName


    private fun defineBackground(position: Position): Int {
        return when (position) {
            Position.FIRST -> R.drawable.item_bgd_dashboard_first_pass
            Position.LAST -> R.drawable.item_bgd_dashboard_last_pass
            Position.SINGLE -> R.drawable.item_bgd_dashboard_single_pass
            else -> R.drawable.item_bgd_dashboard_pass
        }
    }

    // TODO Timezones
    private fun defineExpirationDate(holder: RecyclerItemHolder, date: Long): String {
        with(holder.binding!!.root.context) {
            return String.format(getString(R.string.label_dashboard_passes_valid_thru), SimpleDateFormat("MMM dd").format(date))
        }
    }

    private fun defineVisits(holder: RecyclerItemHolder): String {
        if (data.totalVisits != null) {
            return String.format("%d/%d", data.userVisits, data.totalVisits)
        } else {
            val creationDate = TimeUtil.toDate(data.creationDate!!)
            val expDate = TimeUtil.toDate(data.expirationDate!!)
            val months = Period.between(creationDate, expDate).months
            with(holder.binding!!.root.context) {
                return String.format("%d %s", months, resources.getQuantityString(R.plurals.label_month, months))
            }
        }
    }

}