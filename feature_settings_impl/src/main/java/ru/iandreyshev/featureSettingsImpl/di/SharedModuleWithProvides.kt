package ru.iandreyshev.featureSettingsImpl.di

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import ru.iandreyshev.featureSettingsApi.ISettingsFragmentFactory
import ru.iandreyshev.featureSettingsImpl.ui.fragment.SettingsFragment

@Module
class SharedModuleWithProvides {

    @Provides
    fun provideFragmentFactory() = object : ISettingsFragmentFactory {
        override fun create(): Fragment = SettingsFragment()
    }

}