package ru.iandreyshev.featureMenu.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.iandreyshev.coreui.ui.fragment.BaseFragment
import ru.iandreyshev.coreui.viewModel.observeNotNull
import ru.iandreyshev.coreui.viewModel.viewModel
import ru.iandreyshev.featureMenu.R
import ru.iandreyshev.featureMenu.di.FeatureMenuComponent
import ru.iandreyshev.featureMenu.viewModel.SettingsViewModel

class SettingsFragment : BaseFragment() {

    private lateinit var mViewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FeatureMenuComponent.get().inject(this)

        mViewModel = viewModel(viewModelFactory)

        mViewModel.apply {
            observeNotNull(waiting, ::handleWaiting)
        }
    }

    private fun handleWaiting(isWaiting: Boolean) {
    }

}
