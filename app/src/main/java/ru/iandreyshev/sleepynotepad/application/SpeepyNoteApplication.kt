package ru.iandreyshev.sleepynotepad.application

import android.app.Application
import ru.iandreyshev.featureAlarmImpl.di.FeatureAlarmComponent
import ru.iandreyshev.featureDreams.di.FeatureDreamsComponent
import ru.iandreyshev.featureMenu.di.FeatureMenuComponent
import ru.iandreyshev.featureSettingsImpl.di.FeatureSettingsComponent
import ru.iandreyshev.sleepynotepad.proxy.ComponentFactory
import ru.iandreyshev.sleepynotepad.proxy.navigation.FeatureMenuMenuNavigator
import ru.iandreyshev.sleepynotepad.proxy.navigation.FeatureMenuSplashNavigator
import javax.inject.Inject

class SpeepyNoteApplication : Application() {

    companion object {
        lateinit var instance: Application
    }

    @Inject
    internal lateinit var componentFactory: ComponentFactory

    override fun onCreate() {
        super.onCreate()
        instance = this

        componentFactory = ComponentFactory(this, FeatureMenuMenuNavigator(this), FeatureMenuSplashNavigator(this))

        FeatureSettingsComponent.init(componentFactory.featureSettingsComponent())
        FeatureDreamsComponent.init(componentFactory.featureDreamsComponent())
        FeatureAlarmComponent.init(componentFactory.featureAlarmComponent())
        FeatureMenuComponent.init(componentFactory.featureMenuComponent())
    }

}
