package ru.iandreyshev.featureDreamsApi.api

import ru.iandreyshev.featureDreamsApi.useCase.IClearDreamsUseCase

interface IFeatureDreamsApi {
    val dreamsRepository: IDreamsRepository
    val dreamsListFragmentFactory: IDreamsListFragmentFactory
    val clearStorageUseCase: IClearDreamsUseCase
}
