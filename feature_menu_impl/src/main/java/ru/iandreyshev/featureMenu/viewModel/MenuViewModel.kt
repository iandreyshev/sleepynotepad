package ru.iandreyshev.featureMenu.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.iandreyshev.featureDreamsApi.api.IDreamsRepository
import ru.iandreyshev.featureMenu.di.dependencies.IMenuNavigator
import ru.iandreyshev.vext.liveData.mutableLiveDataOf
import javax.inject.Inject

class MenuViewModel
@Inject constructor(
        private val menuNavigator: IMenuNavigator,
        dreamsRepository: IDreamsRepository
) : ViewModel() {

    enum class MenuState {
        DREAMS_LIST,
        ALARM,
        SETTINGS
    }

    val menuState: LiveData<MenuState>
        get() = mMenuState
    val dreamsAvailability: LiveData<Boolean>
        get() = mDreamsAvailability

    private val mMenuState = mutableLiveDataOf(MenuState.DREAMS_LIST)
    private val mDreamsAvailability = mutableLiveDataOf(false)

    private val mDreamsCountSubscription: Disposable

    init {
        mDreamsCountSubscription = dreamsRepository.dreams
                .map { it.isNotEmpty() }
                .subscribe { mDreamsAvailability.value = it }
    }

    fun onCreateDream() = menuNavigator.onCreateDream()

    fun onNewMenuState(state: MenuState) {
        mMenuState.value = state
    }

    override fun onCleared() {
        mDreamsCountSubscription.dispose()
    }

}
