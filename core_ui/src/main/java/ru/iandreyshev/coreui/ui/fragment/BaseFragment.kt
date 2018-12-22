package ru.iandreyshev.coreui.ui.fragment

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import ru.iandreyshev.coreui.viewModel.activityViewModel
import ru.iandreyshev.coreui.viewModel.viewModel
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit = {}): T =
            viewModel(viewModelFactory, body)

    protected inline fun <reified T : ViewModel> activityViewModel(body: T.() -> Unit = {}): T =
            activityViewModel(viewModelFactory, body)

}
