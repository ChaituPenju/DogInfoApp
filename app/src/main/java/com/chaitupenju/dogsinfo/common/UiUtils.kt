package com.chaitupenju.dogsinfo.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ViewGroup.getLayoutInflater(): LayoutInflater {
    return LayoutInflater.from(this.context)
}