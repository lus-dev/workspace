package lus.areapass.di

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


inline fun <reified T : ViewModel> FragmentActivity.viewModel(crossinline initialBlock: (T) -> Unit) =
    viewModels<T> {
        object : ViewModelProvider.Factory {
            override fun <F : ViewModel> create(modelClass: Class<F>): F {
                return ViewModelProvider(this@viewModel).get(modelClass)
                    .apply { initialBlock(this as T) }
            }
        }
    }

inline fun <reified T : ViewModel> Fragment.viewModel(crossinline initialBlock: (T) -> Unit) =
    viewModels<T> {
        object : ViewModelProvider.Factory {
            override fun <F : ViewModel> create(modelClass: Class<F>): F {
                return ViewModelProvider(this@viewModel).get(modelClass)
                    .apply { initialBlock(this as T) }
            }
        }
    }