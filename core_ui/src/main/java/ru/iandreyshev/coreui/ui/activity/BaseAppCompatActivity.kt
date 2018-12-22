package ru.iandreyshev.coreui.ui.activity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import ru.iandreyshev.coreui.viewModel.viewModel
import javax.inject.Inject

abstract class BaseAppCompatActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit = {}): T =
            viewModel(viewModelFactory, body)

}
