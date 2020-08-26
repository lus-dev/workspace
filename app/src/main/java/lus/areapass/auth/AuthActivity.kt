package lus.areapass.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import lus.areapass.BaseActivity
import lus.areapass.auth.viewmodels.AuthenticationViewModel
import lus.areapass.dashboard.DashboardActivity
import lus.areapass.di.injector
import lus.areapass.di.viewModel
import lus.areapass.entities.person.User


class AuthActivity : BaseActivity<AuthenticationViewModel>() {

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, AuthActivity::class.java)
            context.startActivity(intent)
        }
    }

    override val viewModel by viewModel {
        injector.authenticationViewModel.apply {
            onSignIn.observe(this@AuthActivity, Observer(::navigateToHome))
            onCreateAccount.observe(this@AuthActivity, Observer { onCreateAccount() })
            onResetPassword.observe(this@AuthActivity, Observer { onResetPassword() })
            onBack.observe(this@AuthActivity, Observer { onBackPressed() })
            onRefreshUi.observe(this@AuthActivity, Observer { binding.invalidateAll() })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment(SignInFragment::class.java)
    }

    private fun navigateToHome(user: User) {
        viewModel.rememberUserLocally(user)
        DashboardActivity.navigate(this)
        finish()
    }

    private fun onCreateAccount() {
        loadFragment(type = CreateAccountFragment::class.java, addToBackStack = true)
    }

    private fun onResetPassword() {
        loadFragment(type = ResetPasswordFragment::class.java, addToBackStack = true)
    }

}