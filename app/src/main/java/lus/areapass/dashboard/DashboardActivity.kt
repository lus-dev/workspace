package lus.areapass.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import lus.areapass.BaseActivity
import lus.areapass.BuildConfig
import lus.areapass.R
import lus.areapass.account.AccountActivity
import lus.areapass.account.navigate
import lus.areapass.auth.AuthActivity
import lus.areapass.auth.navigate
import lus.areapass.dashboard.viewmodels.DashboardViewModel
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
            .apply {
                onViewAreas.observe(this@DashboardActivity, Observer { navigateToAreas() })
                onViewAccount.observe(this@DashboardActivity, Observer { navigateToAccount() })
            }
    }


    override fun getLayoutId() = R.layout.activity_dashboard

    private fun navigateToAreas() {

    }

    private fun navigateToAccount() {
        AccountActivity.navigate(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableRecyclerAdapterBinding()
        viewModel.showToolbar(title = getString(R.string.app_name), showBack = true)
    }

    override fun onResume() {
        super.onResume()
        val user = viewModel.getRememberedUser()
        if (checkAuthorization(user)) {
            buildLabels(user)
            viewModel.refreshPasses(user.id!!)
        } else {
            AuthActivity.navigate(this)
            finish()
        }
    }

    private fun enableRecyclerAdapterBinding() {
        binding.lifecycleOwner = this
    }

    private fun buildLabels(user: User) {
        viewModel.username.value = String.format(getString(R.string.label_dashboard_username), user.getFullName())
        viewModel.trial.value = String.format(getString(R.string.label_dashboard_trial), "30 days") // TODO Implement trials
        viewModel.about.value = String.format(getString(R.string.label_dashboard_about), BuildConfig.VERSION_NAME)
    }

    private fun checkAuthorization(user: User) = user.id != null

}