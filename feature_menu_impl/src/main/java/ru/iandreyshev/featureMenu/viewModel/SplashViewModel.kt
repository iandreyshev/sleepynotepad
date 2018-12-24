package ru.iandreyshev.featureMenu.viewModel

import android.arch.lifecycle.ViewModel
import ru.iandreyshev.featureMenu.di.dependencies.ISplashNavigator
import ru.iandreyshev.featureMenu.useCase.IStartupUseCase
import javax.inject.Inject

class SplashViewModel
@Inject constructor(
        private val navigator: ISplashNavigator,
        startupUseCase: IStartupUseCase
) : ViewModel() {

    init {
        startupUseCase().ignoreElement().subscribe {
            navigator.onCompleted()
        }
    }

}
