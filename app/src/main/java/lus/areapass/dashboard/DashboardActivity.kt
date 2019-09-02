package lus.areapass.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import lus.areapass.BaseActivity
import lus.areapass.R
import lus.areapass.di.injector
import lus.areapass.di.viewModel
import lus.areapass.entities.User


fun DashboardActivity.Companion.navigate(context: Context) {
    val intent = Intent(context, DashboardActivity::class.java)
    context.startActivity(intent)
}

class DashboardActivity : BaseActivity<DashboardViewModel>() {
    companion object;

    override val viewModel by viewModel {
        injector.dashboardViewModel // .create(User())
    }

    override fun getLayoutId() = R.layout.activity_dashboard

}