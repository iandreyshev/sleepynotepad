package ru.iandreyshev.featureDreams.useCase.impl

import io.reactivex.Completable
import ru.iandreyshev.featureDreams.storage.IDreamsStorage
import ru.iandreyshev.featureDreamsApi.useCase.IClearDreamsUseCase
import javax.inject.Inject

class ClearDreamsUseCase
@Inject constructor(
        private val storage: IDreamsStorage
) : IClearDreamsUseCase {

    override fun invoke(): Completable = Completable.create {
        storage.clear()
        it.onComplete()
    }

}
