package ru.iandreyshev.featureAlarm

import android.support.v4.app.Fragment

interface IAlarmFragmentFactory {
    fun create(): Fragment
}