package ru.iandreyshev.coreui.viewModel

import android.arch.lifecycle.LiveData
import ru.iandreyshev.vext.liveData.mutableLiveDataOf

class WaitingViewModel(default: Boolean = false) {

    val observable: LiveData<Boolean>
        get() = mWaitState

    val isWait: Boolean
        get() = mWaitState.value ?: false

    private val mWaitState = mutableLiveDataOf(default)

    fun start() {
        mWaitState.value = true
    }

    fun stop() {
        mWaitState.value = false
    }

}