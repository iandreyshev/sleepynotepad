package ru.iandreyshev.featureMenu.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import ru.iandreyshev.coreui.ui.activity.BaseAppCompatActivity
import ru.iandreyshev.coreui.viewModel.observeNotNull
import ru.iandreyshev.featureAlarm.IAlarmFragmentFactory
import ru.iandreyshev.featureDreamsApi.api.IDreamsListFragmentFactory
import ru.iandreyshev.featureMenu.R
import ru.iandreyshev.featureMenu.di.FeatureMenuComponent
import ru.iandreyshev.featureMenu.viewModel.MenuViewModel
import ru.iandreyshev.featureSettingsApi.ISettingsFragmentFactory
import javax.inject.Inject

class MenuActivity : BaseAppCompatActivity() {

    @Inject
    lateinit var mDreamsListFragmentFactory: IDreamsListFragmentFactory
    @Inject
    lateinit var mAlarmFragmentFactory: IAlarmFragmentFactory
    @Inject
    lateinit var mSettingsFragmentFactory: ISettingsFragmentFactory

    private val mViewModel by lazy { viewModel<MenuViewModel>() }

    private val mDreamsListFragment: Fragment?
        get() = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_DREAMS_LIST)
    private val mAlarmFragment: Fragment?
        get() = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_ALARM)
    private val mSettingsFragment: Fragment?
        get() = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_SETTINGS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        FeatureMenuComponent.get().inject(this)

        initDreamsListFragment()
        initAlarmFragment()
        initSettingsFragment()

        mViewModel.apply {
            observeNotNull(dreamsAvailability, ::handleDreamsAvailability)
        }
    }

    private fun initDreamsListFragment() {
        mDreamsListFragment ?: return
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_dreams_list, mDreamsListFragmentFactory.create(), FRAGMENT_TAG_DREAMS_LIST)
                .commit()
    }

    private fun initAlarmFragment() {
        mAlarmFragment ?: return
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_alarm, mAlarmFragmentFactory.create(), FRAGMENT_TAG_ALARM)
                .commit()
    }

    private fun initSettingsFragment() {
        mSettingsFragment ?: return
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_settings, mSettingsFragmentFactory.create(), FRAGMENT_TAG_SETTINGS)
                .commit()
    }

    private fun handleDreamsAvailability(isAvailable: Boolean) {
        refreshActionBar()
    }

    private fun refreshActionBar() {
    }

    companion object {
        private const val FRAGMENT_TAG_DREAMS_LIST = "fragment.dreamsList"
        private const val FRAGMENT_TAG_ALARM = "fragment.alarm"
        private const val FRAGMENT_TAG_SETTINGS = "fragment.settings"
    }

}
