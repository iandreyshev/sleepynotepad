package ru.iandreyshev.featureDreams.useCase.impl

import io.reactivex.Single
import ru.iandreyshev.featureDreams.storage.IDreamsStorage
import ru.iandreyshev.featureDreams.useCase.ISaveDreamUseCase
import ru.iandreyshev.featureDreamsApi.domain.DreamProperties
import ru.iandreyshev.featureDreams.domain.SaveResult
import ru.iandreyshev.featureDreams.mapping.toEntity
import ru.iandreyshev.featureDreamsApi.domain.DreamKey
import javax.inject.Inject

class SaveDreamUseCase
@Inject constructor(
    private val storage: IDreamsStorage
) : ISaveDreamUseCase {

    override fun invoke(properties: DreamProperties, key: DreamKey?): Single<SaveResult> = Single.create {
        val dream = properties.clear()

        dream.getPropertiesError()?.run {
            it.onSuccess(this)
            return@create
        }

        storage.save(dream.toEntity())
        it.onSuccess(SaveResult.SUCCESS)
    }

    private fun DreamProperties.getPropertiesError(): SaveResult? = when {
        description.isEmpty() -> SaveResult.ERROR_EMPTY_DREAM
        description.isBlank() -> SaveResult.ERROR_BLANK_DREAM
        description.length > MAX_DREAM_DESCRIPTION_LENGTH -> SaveResult.ERROR_LARGE_DREAM
        else -> null
    }

    private fun DreamProperties.clear(): DreamProperties =
        copy(description = description.trim())

    companion object {
        private const val MAX_DREAM_DESCRIPTION_LENGTH = 100
    }

}