package com.tusxdie.weatherappsoftengineering.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MVIViewModel<S : MVIState, I : MVIIntent>(initialState: S) : ViewModel() {
    protected val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> get() = _state.asStateFlow()

    abstract fun onIntent(intent: I)
}
