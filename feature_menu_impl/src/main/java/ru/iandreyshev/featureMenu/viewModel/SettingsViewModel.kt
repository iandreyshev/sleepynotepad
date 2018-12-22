package ru.iandreyshev.featureMenu.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.iandreyshev.coreui.viewModel.WaitingViewModel
import ru.iandreyshev.featureMenu.di.dependencies.IMenuNavigator
import javax.inject.Inject

class SettingsViewModel
@Inject constructor(
        private val navigator: IMenuNavigator
) : ViewModel() {

    val waiting: LiveData<Boolean>
        get() = mWaitingObservable.observable

    private val mWaitingObservable = WaitingViewModel()

    private var mLogOutUseCaseSubscription: Disposable? = null
    private var mDeleteUserUseCaseSubscription: Disposable? = null

    override fun onCleared() {
        mLogOutUseCaseSubscription?.dispose()
        mDeleteUserUseCaseSubscription?.dispose()
    }

}
