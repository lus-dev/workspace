package lus.areapass.di

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel


inline fun <reified T : ViewModel> FragmentActivity.viewModel(crossinline initializer: (T) -> Unit) =
    viewModels<T>().apply { initializer(this.value) }

inline fun <reified T : ViewModel> Fragment.viewModel(crossinline initializer: (T) -> Unit) =
    viewModels<T>().apply { initializer(this.value) }