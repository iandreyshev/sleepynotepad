package ru.iandreyshev.featureMenu.useCase

import io.reactivex.Single
import javax.inject.Inject

class StartupUseCase
@Inject constructor() : IStartupUseCase {

    override fun invoke(): Single<Boolean> = Single.create {
        it.onSuccess(true)
    }

}
