package ru.iandreyshev.featureMenu.di

import dagger.Component
import ru.iandreyshev.featureAlarm.IFeatureAlarmApi
import ru.iandreyshev.featureDreamsApi.api.IFeatureDreamsApi
import ru.iandreyshev.featureMenu.di.dependencies.IFeatureMenuDependencies
import ru.iandreyshev.featureMenu.di.dependencies.IMenuNavigator
import ru.iandreyshev.featureMenu.di.dependencies.ISplashNavigator
import ru.iandreyshev.featureMenu.ui.activity.MenuActivity
import ru.iandreyshev.featureMenu.ui.activity.SplashActivity
import ru.iandreyshev.featureSettingsApi.IFeatureSettingsApi
import javax.inject.Singleton

@Component(
        modules = [
            FeatureMenuViewModelModule::class,
            FeatureMenuUseCaseModule::class],
        dependencies = [
            IFeatureMenuDependencies::class]
)
@Singleton
abstract class FeatureMenuComponent {

    abstract fun inject(activity: MenuActivity)
    abstract fun inject(activity: SplashActivity)

    @Component(
            dependencies = [
                IFeatureDreamsApi::class,
                IFeatureAlarmApi::class,
                IFeatureSettingsApi::class,
                ISplashNavigator::class,
                IMenuNavigator::class]
    )
    abstract class DependenciesComponent : IFeatureMenuDependencies

    companion object {
        fun init(component: FeatureMenuComponent) {
            sInstance = component
        }

        fun get() = sInstance

        @Volatile
        private lateinit var sInstance: FeatureMenuComponent
    }

}
