package lus.areapass.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lus.areapass.BaseActivity
import lus.areapass.R
import lus.areapass.account.AccountActivity
import lus.areapass.account.navigate
import lus.areapass.auth.AuthActivity
import lus.areapass.dashboard.viewmodels.DashboardViewModel
import lus.areapass.di.viewModel
import lus.areapass.pass.PassActivity


@AndroidEntryPoint
class DashboardActivity : BaseActivity<DashboardViewModel>() {

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, DashboardActivity::class.java)
            context.startActivity(intent)
        }
    }

    override val viewModel by viewModel<DashboardViewModel> {
        with(it) {
            onViewAreas.observe(this@DashboardActivity, Observer { navigateToAreas() })
            onViewAccount.observe(this@DashboardActivity, Observer { navigateToAccount() })
        }
    }

    override fun getLayoutId() = R.layout.activity_dashboard

    private fun navigateToAreas() {
        PassActivity.navigate(this)
    }

    private fun navigateToAccount() {
        AccountActivity.navigate(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showToolbar(title = getString(R.string.app_name), showBack = false)
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.authorized()) {
            AuthActivity.navigate(this)
            finish()
        }
    }

}