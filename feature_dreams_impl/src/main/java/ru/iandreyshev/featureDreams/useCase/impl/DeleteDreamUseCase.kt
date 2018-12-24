package ru.iandreyshev.featureDreams.useCase.impl

import io.reactivex.Single
import ru.iandreyshev.featureDreams.storage.IDreamsStorage
import ru.iandreyshev.featureDreams.useCase.IDeleteDreamUseCase
import ru.iandreyshev.featureDreams.domain.DeleteResult
import ru.iandreyshev.featureDreamsApi.domain.DreamKey
import javax.inject.Inject

class DeleteDreamUseCase
@Inject constructor(
        private val storage: IDreamsStorage
) : IDeleteDreamUseCase {

    override fun invoke(key: DreamKey): Single<DeleteResult> = Single.create {
        storage.delete(key)
        it.onSuccess(DeleteResult.SUCCESS)
    }

}