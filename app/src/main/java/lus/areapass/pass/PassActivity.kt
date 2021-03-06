package lus.areapass.pass

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lus.areapass.BaseActivity
import lus.areapass.di.viewModel
import lus.areapass.pass.viewmodels.PassRootViewModel


@AndroidEntryPoint
class PassActivity : BaseActivity<PassRootViewModel>() {

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, PassActivity::class.java)
            context.startActivity(intent)
        }
    }

    override val viewModel by viewModel<PassRootViewModel> {
        with(it) {
            onCreatePass.observe(this@PassActivity, Observer { onCreatePass() })
            onBack.observe(this@PassActivity, Observer { onBackPressed() })
            onRefreshUi.observe(this@PassActivity, Observer { binding.invalidateAll() })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreatePass()
    }

    private fun onCreatePass() {
        loadFragment(CreatePassFragment::class.java)
    }

}