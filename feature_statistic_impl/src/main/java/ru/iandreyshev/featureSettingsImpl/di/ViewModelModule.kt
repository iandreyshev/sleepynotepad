package ru.iandreyshev.featureSettingsImpl.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.iandreyshev.coreui.di.viewModel.MapBasedViewModelFactory
import ru.iandreyshev.coreui.di.viewModel.ViewModelKey
import ru.iandreyshev.featureSettingsImpl.viewModel.SettingsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: MapBasedViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

}