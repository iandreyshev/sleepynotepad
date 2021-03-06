package ru.iandreyshev.featureDreams.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import io.reactivex.disposables.Disposable
import ru.iandreyshev.coreUtils.rx.ioToMain
import ru.iandreyshev.coreUtils.rx.subscribe
import ru.iandreyshev.coreui.viewModel.SingleLiveEvent
import ru.iandreyshev.coreui.viewModel.SingleLiveTypedEvent
import ru.iandreyshev.coreui.viewModel.WaitingViewModel
import ru.iandreyshev.featureDreams.domain.*
import ru.iandreyshev.featureDreams.useCase.IDeleteDreamUseCase
import ru.iandreyshev.featureDreamsApi.api.IDreamsRepository
import ru.iandreyshev.featureDreamsApi.domain.*
import ru.iandreyshev.vext.liveData.mutableLiveDataOf
import javax.inject.Inject

class DreamViewModel
@Inject constructor(
        private val deleteUseCase: IDeleteDreamUseCase,
        dreamsRepository: IDreamsRepository,
        bundle: Bundle?
) : ViewModel() {

    val deleteDreamWaiting: LiveData<Boolean>
        get() = mDeleteWaitingViewModel.observable
    val dream: LiveData<Dream>
        get() = mDreamViewModel

    val deletingResultEvent: LiveData<DeleteResult>
        get() = mDeletingErrorEvent
    val loadingErrorEvent: LiveData<Unit>
        get() = mLoadingErrorEvent
    val unknownErrorEvent: LiveData<Unit>
        get() = mUnknownErrorEvent

    private val mDeleteWaitingViewModel = WaitingViewModel()
    private val mDreamViewModel = mutableLiveDataOf<Dream>()

    private val mDeletingErrorEvent = SingleLiveTypedEvent<DeleteResult>()
    private val mLoadingErrorEvent = SingleLiveEvent()
    private val mUnknownErrorEvent = SingleLiveEvent()

    private var mLoadDreamSubscription: Disposable? = null
    private var mDeleteDreamSubscription: Disposable? = null

    init {
        val dream: Dream? = Dream.create(bundle)
        if (dream == null) {
            mLoadingErrorEvent.call()
        } else {
            mDreamViewModel.value = dream
            mLoadDreamSubscription = dreamsRepository.getDream(dream.key)
                    .subscribe(::handleLoadDreamResult, ::handleLoadDreamError)
        }
    }

    fun delete() {
        val dream = mDreamViewModel.value ?: return
        mDeleteWaitingViewModel.start()
        mDeleteDreamSubscription = deleteUseCase.invoke(dream.key)
                .ioToMain()
                .subscribe(::handleDeleteResult, ::handleDeleteError) {
                    mDeleteWaitingViewModel.stop()
                }
    }

    override fun onCleared() {
        mDeleteDreamSubscription?.dispose()
    }

    private fun handleLoadDreamResult(result: LoadDreamResult) {
        when (result) {
            is LoadingDreamCompleted -> {
            }
            is UpdateDreamCompleted -> {
                mDreamViewModel.value = mDreamViewModel.value
                        ?.copy(properties = result.properties)
            }
            is ErrorDreamDeleted -> mLoadingErrorEvent.call()
        }
    }

    private fun handleLoadDreamError(error: Throwable) {
        // TODO: Handle unknown error
        error.printStackTrace()
    }

    private fun handleDeleteResult(result: DeleteResult) {
        mDeletingErrorEvent.value = result
    }

    private fun handleDeleteError(error: Throwable) {
        mDeletingErrorEvent.value = DeleteResult.ERROR_UNDEFINED
        error.printStackTrace()
    }

}
