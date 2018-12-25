package ru.iandreyshev.featureSettingsImpl.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.iandreyshev.coreui.viewModel.WaitingViewModel
import javax.inject.Inject

class SettingsViewModel
@Inject constructor() : ViewModel() {

    val waiting: LiveData<Boolean>
        get() = mWaitingObservable.observable

    private val mWaitingObservable = WaitingViewModel()

}
