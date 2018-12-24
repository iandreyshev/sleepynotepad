package ru.iandreyshev.featureMenu.di.dependencies

import ru.iandreyshev.featureAlarm.IAlarmFragmentFactory
import ru.iandreyshev.featureDreamsApi.api.IDreamsListFragmentFactory
import ru.iandreyshev.featureDreamsApi.api.IDreamsRepository
import ru.iandreyshev.featureDreamsApi.useCase.IClearDreamsUseCase
import ru.iandreyshev.featureSettingsApi.ISettingsFragmentFactory

interface IFeatureMenuDependencies {
    val dreamsListFragmentFactory: IDreamsListFragmentFactory
    val alarmFragmentFactory: IAlarmFragmentFactory
    val settingsFragmentFactory: ISettingsFragmentFactory
    val menuNavigator: IMenuNavigator
    val splashNavigator: ISplashNavigator
    val dreamsRepository: IDreamsRepository
    val clearDreamsUseCase: IClearDreamsUseCase
}
