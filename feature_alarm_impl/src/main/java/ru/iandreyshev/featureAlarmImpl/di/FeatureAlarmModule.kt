package ru.iandreyshev.featureAlarmImpl.di

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import ru.iandreyshev.featureAlarm.IAlarmFragmentFactory
import ru.iandreyshev.featureAlarmImpl.ui.fragment.AlarmFragment

@Module
class FeatureAlarmModule {
    @Provides
    fun provideAlarmFragmentFactory() = object : IAlarmFragmentFactory {
        override fun create(): Fragment = AlarmFragment()
    }
}