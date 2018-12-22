package ru.iandreyshev.coreUtils.di.context

import android.content.Context

interface IContextProvider {
    val context: Context
}

fun Context.asProvider(): IContextProvider = object : IContextProvider {
    override val context: Context = this@asProvider
}
