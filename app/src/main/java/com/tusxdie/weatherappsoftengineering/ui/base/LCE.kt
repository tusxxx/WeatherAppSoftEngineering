package com.tusxdie.weatherappsoftengineering.ui.base

sealed interface LCE {
    data object Loading : LCE
    data object Loaded : LCE
    data class Error(val message: String) : LCE
}

fun LCE.isLoading() = this is LCE.Loading
fun LCE.isLoaded() = this is LCE.Loaded
fun LCE.isError() = this is LCE.Error