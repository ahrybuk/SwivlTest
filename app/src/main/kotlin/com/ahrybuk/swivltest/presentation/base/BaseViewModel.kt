package com.ahrybuk.swivltest.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {

    protected val subscriptions = CompositeDisposable()

    override fun onCleared() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        super.onCleared()
    }
}