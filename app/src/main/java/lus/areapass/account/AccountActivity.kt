package lus.areapass.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import lus.areapass.BaseActivity
import lus.areapass.account.viewmodels.AccountViewModel
import lus.areapass.di.injector
import lus.areapass.di.viewModel


fun AccountActivity.Companion.navigate(context: Context) {
    val intent = Intent(context, AccountActivity::class.java)
    context.startActivity(intent)
}

class AccountActivity : BaseActivity<AccountViewModel>() {
    companion object;

    override val viewModel by viewModel {
        injector.accountViewModel.apply {
            onChangePassword.observe(this@AccountActivity, Observer { onChangePassword() })
            onSignOut.observe(this@AccountActivity, Observer { onSignOut() })
            onBack.observe(this@AccountActivity, Observer { onBackPressed() })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment(AccountDetailsFragment::class.java)
    }

    private fun onChangePassword() {
//        loadFragment(type = CreateAccountFragment::class.java, addToBackStack = true)
    }

    private fun onSignOut() {
        finish()
    }

}