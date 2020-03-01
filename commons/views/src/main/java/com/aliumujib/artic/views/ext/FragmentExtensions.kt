package com.aliumujib.artic.views.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Generic view model provider.
 *
 * @param key The key to use to identify the ViewModel.
 * @param factory Function creates a new instance of the ViewModel.
 * @return A ViewModel that is an instance of the given type [VM].
 * @see ViewModel
 */
@Suppress("UNCHECKED_CAST")
fun <VM : ViewModel> Fragment.viewModel(
    key: String? = null,
    factory: () -> VM
): VM {
    val factoryViewModel = factory()
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return factoryViewModel as T
        }
    }

    return if (key != null) {
        ViewModelProviders.of(this, viewModelProviderFactory).get(key, factoryViewModel::class.java)
    } else {
        ViewModelProviders.of(this, viewModelProviderFactory).get(factoryViewModel::class.java)
    }
}
