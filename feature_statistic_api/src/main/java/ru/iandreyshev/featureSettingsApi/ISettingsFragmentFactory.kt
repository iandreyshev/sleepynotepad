package ru.iandreyshev.featureSettingsApi

import android.support.v4.app.Fragment

interface ISettingsFragmentFactory {
    fun create(): Fragment
}