package ru.iandreyshev.featureDreams.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import io.reactivex.disposables.Disposable
import ru.iandreyshev.coreUtils.rx.ioToMain
import ru.iandreyshev.coreUtils.rx.subscribe
import ru.iandreyshev.coreui.viewModel.DialogViewModel
import ru.iandreyshev.coreui.viewModel.SingleLiveEvent
import ru.iandreyshev.coreui.viewModel.WaitingViewModel
import ru.iandreyshev.featureDreamsApi.domain.DreamProperties
import ru.iandreyshev.featureDreams.domain.SaveResult
import ru.iandreyshev.featureDreams.ui.activity.DreamEditorActivity
import ru.iandreyshev.featureDreams.useCase.ISaveDreamUseCase
import ru.iandreyshev.featureDreamsApi.domain.Dream
import ru.iandreyshev.featureDreamsApi.domain.DreamKey
import ru.iandreyshev.vext.liveData.mutableLiveDataOf

class DreamEditorViewModel(
        private val saveDreamUseCase: ISaveDreamUseCase
) : ViewModel() {

    val dream: LiveData<Dream>
        get() = mDreamViewModel
    val saveResult: LiveData<SaveResult>
        get() = mErrorViewModel.observable
    val saveWaiting: LiveData<Boolean>
        get() = mWaitingViewModel.observable
    val closeEvent: LiveData<Unit>
        get() = mCloseEvent

    private var mDreamKey: DreamKey? = null

    private val mDreamViewModel = mutableLiveDataOf<Dream>()
    private val mErrorViewModel = DialogViewModel<SaveResult>()
    private val mWaitingViewModel = WaitingViewModel()
    private val mCloseEvent = SingleLiveEvent()

    private var mSaveDreamSubscription: Disposable? = null

    fun setDreamToEdit(bundle: Bundle?) {
        val dreamBundle = bundle?.getBundle(DreamEditorActivity.KEY_DREAM)
        mDreamViewModel.postValue(Dream.create(dreamBundle))
        mDreamKey = mDreamViewModel.value?.key
    }

    fun saveDream(dream: DreamProperties) {
        if (mWaitingViewModel.isWait) {
            return
        }

        mWaitingViewModel.start()
        mSaveDreamSubscription = saveDreamUseCase(dream, mDreamKey)
                .ioToMain()
                .subscribe(::handleSaveResult, ::handleSaveError) {
                    mWaitingViewModel.stop()
                }
    }

    fun onCloseSaveResult() {
        mErrorViewModel.close()
    }

    override fun onCleared() {
        mSaveDreamSubscription?.dispose()
    }

    private fun handleSaveResult(result: SaveResult) {
        if (result == SaveResult.SUCCESS) {
            mCloseEvent()
            return
        }
        mErrorViewModel.update(result)
    }

    private fun handleSaveError(error: Throwable) {
        mErrorViewModel.update(SaveResult.ERROR_UNDEFINED)
        error.printStackTrace()
    }

}
