package ru.iandreyshev.featureSettingsImpl.di

import dagger.Component
import ru.iandreyshev.coreui.ui.fragment.BaseFragment
import ru.iandreyshev.featureSettingsApi.IFeatureSettingsApi

@Component(
    modules = [
        ViewModelModule::class,
        SharedModuleWithProvides::class
    ]
)
abstract class FeatureSettingsComponent : IFeatureSettingsApi {

    abstract fun inject(fragment: BaseFragment)

    companion object {
        private lateinit var sInstance: FeatureSettingsComponent

        fun init(component: FeatureSettingsComponent) {
            sInstance = component
        }

        fun get(): FeatureSettingsComponent {
            return sInstance
        }
    }

}