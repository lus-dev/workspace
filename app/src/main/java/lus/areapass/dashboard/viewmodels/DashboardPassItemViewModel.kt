package lus.areapass.dashboard.viewmodels

import lus.areapass.R
import lus.areapass.entities.Pass
import lus.areapass.utils.TimeUtil
import org.threeten.bp.Period


class DashboardPassItemViewModel constructor(private val data: List<Pass>, private val position: Int) {

//    protected enum class Position {
//        FIRST, LAST, DEFAULT, SINGLE
//    }

    fun getAreaName() = data.get(position).areaName

    fun getBackground(): Int {
        if (data.size == 1) {
            return R.drawable.item_bgd_dashboard_single_pass
        }
        return when (position) {
            0 -> R.drawable.item_bgd_dashboard_first_pass
            (data.size.minus(1)) -> R.drawable.item_bgd_dashboard_last_pass
            else -> R.drawable.item_bgd_dashboard_pass
        }
    }

    // TODO Re-implement
    // TODO Notes: Timezones
    fun getExpirationDate(): String {
//        with(holder.binding!!.root.context) {
//            return String.format(getString(R.string.label_dashboard_passes_valid_thru), SimpleDateFormat("MMM dd").format(date))
//        }
        return "Apr, 25"
    }

    fun getVisits(): String {
        val item = data.get(position)
        if (item.totalVisits != null) {
            return String.format("%d/%d", item.userVisits, item.totalVisits)
        } else {
            val creationDate = TimeUtil.toDate(item.creationDate!!)
            val expDate = TimeUtil.toDate(item.expirationDate!!)
            val months = Period.between(creationDate, expDate).months
//            with(holder.binding!!.root.context) {
//                return String.format("%d %s", months, resources.getQuantityString(R.plurals.label_month, months))
//            }
            return "1 mo."
        }
    }

}