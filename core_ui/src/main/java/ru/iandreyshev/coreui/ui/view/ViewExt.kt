package ru.iandreyshev.coreui.ui.view

import android.view.View

fun View.setOnClickListener(listener: () -> Unit) {
    setOnClickListener { listener.invoke() }
}
