package ru.iandreyshev.sleepynotepad.proxy

import android.app.Application
import ru.iandreyshev.coreUtils.di.context.asProvider
import ru.iandreyshev.featureAlarmImpl.di.DaggerFeatureAlarmComponent
import ru.iandreyshev.featureAlarmImpl.di.FeatureAlarmComponent
import ru.iandreyshev.featureDreams.di.DaggerFeatureDreamsComponent
import ru.iandreyshev.featureDreams.di.DaggerFeatureDreamsComponent_DependenciesComponent
import ru.iandreyshev.featureDreams.di.FeatureDreamsComponent
import ru.iandreyshev.featureMenu.di.DaggerFeatureMenuComponent
import ru.iandreyshev.featureMenu.di.DaggerFeatureMenuComponent_DependenciesComponent
import ru.iandreyshev.featureMenu.di.FeatureMenuComponent
import ru.iandreyshev.featureSettingsImpl.di.DaggerFeatureSettingsComponent
import ru.iandreyshev.featureSettingsImpl.di.FeatureSettingsComponent
import ru.iandreyshev.sleepynotepad.proxy.navigation.FeatureMenuMenuNavigator
import ru.iandreyshev.sleepynotepad.proxy.navigation.FeatureMenuSplashNavigator
import javax.inject.Inject

class ComponentFactory
@Inject constructor(
        private val application: Application,
        private val menuNavigator: FeatureMenuMenuNavigator,
        private val splashNavigator: FeatureMenuSplashNavigator
) {

    fun featureMenuComponent(): FeatureMenuComponent =
            DaggerFeatureMenuComponent.builder()
                    .iFeatureMenuDependencies(DaggerFeatureMenuComponent_DependenciesComponent.builder()
                            .iFeatureDreamsApi(FeatureDreamsComponent.get())
                            .iFeatureAlarmApi(FeatureAlarmComponent.get())
                            .iFeatureSettingsApi(FeatureSettingsComponent.get())
                            .iSplashNavigator(splashNavigator)
                            .iMenuNavigator(menuNavigator)
                            .build())
                    .build()

    fun featureDreamsComponent(): FeatureDreamsComponent =
            DaggerFeatureDreamsComponent.builder()
                    .iFeatureDreamsDependencies(DaggerFeatureDreamsComponent_DependenciesComponent.builder()
                            .iContextProvider(application.asProvider())
                            .build())
                    .build()

    fun featureAlarmComponent(): FeatureAlarmComponent =
            DaggerFeatureAlarmComponent.create()

    fun featureSettingsComponent(): FeatureSettingsComponent =
            DaggerFeatureSettingsComponent.create()

}