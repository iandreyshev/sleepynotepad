package ru.iandreyshev.featureAlarmImpl.di

import dagger.Component
import ru.iandreyshev.featureAlarm.IFeatureAlarmApi

@Component(
    modules = [FeatureAlarmModule::class]
)
abstract class FeatureAlarmComponent : IFeatureAlarmApi {

    companion object {
        private lateinit var mInstance: FeatureAlarmComponent

        fun init(component: FeatureAlarmComponent) {
            mInstance = component
        }

        fun get(): FeatureAlarmComponent {
            return mInstance
        }
    }

}