package com.example.primerproyecto.utils

import android.view.View

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.setState(state: Boolean) {
    if(state){
        this?.visibility = View.VISIBLE
    } else {
        this?.visibility = View.GONE
    }
}

fun View?.isVisible(): Boolean {
    return this?.visibility == View.VISIBLE
}