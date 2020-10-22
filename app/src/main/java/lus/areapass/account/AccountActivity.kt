package lus.areapass.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lus.areapass.BaseActivity
import lus.areapass.account.viewmodels.AccountViewModel
import lus.areapass.di.viewModel


fun AccountActivity.Companion.navigate(context: Context) {
    val intent = Intent(context, AccountActivity::class.java)
    context.startActivity(intent)
}

@AndroidEntryPoint
class AccountActivity : BaseActivity<AccountViewModel>() {
    companion object;

    override val viewModel by viewModel<AccountViewModel> {
        with(it) {
            onChangePassword.observe(this@AccountActivity, Observer { onChangePassword() })
            onShowSubscription.observe(this@AccountActivity, Observer { })
            onSignOut.observe(this@AccountActivity, Observer { onSignOut() })
            onBack.observe(this@AccountActivity, Observer { onBackPressed() })
            onRefreshUi.observe(this@AccountActivity, Observer { binding.invalidateAll() })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            loadFragment(AccountDetailsFragment::class.java)
        }
    }

    private fun onChangePassword() {
        loadFragment(type = ChangePasswordFragment::class.java, addToBackStack = true)
    }

    private fun onShowSubscription() {
        // TODO Add screen navigation
    }

    private fun onSignOut() {
        finish()
    }

}