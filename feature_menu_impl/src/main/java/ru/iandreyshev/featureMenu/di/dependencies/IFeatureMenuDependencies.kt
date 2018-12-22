package ru.iandreyshev.featureMenu.di.dependencies

import ru.iandreyshev.featureDreamsApi.api.IDreamsDiaryFragmentFactory
import ru.iandreyshev.featureDreamsApi.api.IDreamsRepository
import ru.iandreyshev.featureDreamsApi.useCase.IClearDreamsStorageUseCase

interface IFeatureMenuDependencies {
    val mainFragmentFactory: IDreamsDiaryFragmentFactory
    val menuNavigator: IMenuNavigator
    val splashNavigator: ISplashNavigator
    val dreamsRepository: IDreamsRepository
    val clearDreamsUseCase: IClearDreamsStorageUseCase
}
