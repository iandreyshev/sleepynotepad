package ru.iandreyshev.featureDreams.di

import dagger.Binds
import dagger.Module
import ru.iandreyshev.coreUtils.di.scope.PerFeature
import ru.iandreyshev.featureDreams.useCase.*
import ru.iandreyshev.featureDreams.useCase.impl.*
import ru.iandreyshev.featureDreamsApi.useCase.IClearDreamsUseCase

@Module
abstract class UseCaseModule {

    @Binds
    @PerFeature
    abstract fun bindSaveDreamUseCase(useCase: SaveDreamUseCase): ISaveDreamUseCase

    @Binds
    @PerFeature
    abstract fun bindDeleteDreamUseCase(useCase: DeleteDreamUseCase): IDeleteDreamUseCase

    @Binds
    @PerFeature
    abstract fun bindClearDreamsStorageUseCase(useCase: ClearDreamsUseCase): IClearDreamsUseCase

}
