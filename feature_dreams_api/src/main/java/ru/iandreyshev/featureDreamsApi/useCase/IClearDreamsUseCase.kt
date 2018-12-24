package ru.iandreyshev.featureDreamsApi.useCase

import io.reactivex.Completable

interface IClearDreamsUseCase {
    operator fun invoke(): Completable
}
