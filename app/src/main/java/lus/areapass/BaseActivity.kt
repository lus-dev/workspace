package lus.areapass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit


abstract class BaseActivity<N : Navigator> : AppCompatActivity() {

    abstract val viewModel: N
    protected val binding: ViewDataBinding by lazy {
        DataBindingUtil.setContentView<ViewDataBinding>(this@BaseActivity, getLayoutId())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.navigator, viewModel)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onBackPressed() {
        if (hasFragments()) {
            getTopFragment()?.let {
                if (!it.isProcessing()) {
                    supportFragmentManager.popBackStack()
                }
            }
            return
        }
        super.onBackPressed()
    }

    protected open fun getLayoutId() = R.layout.activity_fragment_based

    protected fun loadFragment(type: Class<out Fragment>, extras: Bundle? = null, addToBackStack: Boolean = false) {
        supportFragmentManager.commit {
            val fragment = type.newInstance().apply { arguments = extras }
            replace(R.id.content, fragment)
            if (addToBackStack) {
                addToBackStack(type.name)
            }
        }
    }

    private fun hasFragments() = supportFragmentManager.backStackEntryCount > 0

    private fun getTopFragment() = supportFragmentManager.findFragmentById(R.id.content) as? BaseFragment<*,*,*>

}