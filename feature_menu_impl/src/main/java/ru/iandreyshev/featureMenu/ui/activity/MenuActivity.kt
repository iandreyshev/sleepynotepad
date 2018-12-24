package ru.iandreyshev.featureMenu.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_menu.*
import ru.iandreyshev.coreui.ui.activity.BaseAppCompatActivity
import ru.iandreyshev.coreui.viewModel.observeNotNull
import ru.iandreyshev.featureAlarm.IAlarmFragmentFactory
import ru.iandreyshev.featureDreamsApi.api.IDreamsListFragmentFactory
import ru.iandreyshev.featureMenu.R
import ru.iandreyshev.featureMenu.di.FeatureMenuComponent
import ru.iandreyshev.featureMenu.viewModel.MenuViewModel
import ru.iandreyshev.featureSettingsApi.ISettingsFragmentFactory
import ru.iandreyshev.vext.view.gone
import ru.iandreyshev.vext.view.visible
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

        initBottomNavigation()
        initDreamsListFragment()
        initAlarmFragment()
        initSettingsFragment()

        mViewModel.apply {
            observeNotNull(dreamsAvailability, ::handleDreamsAvailability)
            observeNotNull(menuState, ::handleMenuState)
        }
    }

    private fun initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_item_dreams_list -> {
                    mViewModel.onNewMenuState(MenuViewModel.MenuState.DREAMS_LIST)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_item_alarm -> {
                    mViewModel.onNewMenuState(MenuViewModel.MenuState.ALARM)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_item_settings -> {
                    mViewModel.onNewMenuState(MenuViewModel.MenuState.SETTINGS)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun initDreamsListFragment() {
        mDreamsListFragment ?: run {
            supportFragmentManager
                .beginTransaction()
                .add(fragment_dreams_list.id, mDreamsListFragmentFactory.create(), FRAGMENT_TAG_DREAMS_LIST)
                .commit()
        }
    }

    private fun initAlarmFragment() {
        mAlarmFragment ?: run {
            supportFragmentManager
                .beginTransaction()
                .add(fragment_alarm.id, mAlarmFragmentFactory.create(), FRAGMENT_TAG_ALARM)
                .commit()
        }
    }

    private fun initSettingsFragment() {
        mSettingsFragment ?: run {
            supportFragmentManager
                .beginTransaction()
                .add(fragment_settings.id, mSettingsFragmentFactory.create(), FRAGMENT_TAG_SETTINGS)
                .commit()
        }
    }

    private fun handleDreamsAvailability(isAvailable: Boolean) {
        refreshActionBar()
    }

    private fun handleMenuState(menuState: MenuViewModel.MenuState) {
        fragment_dreams_list.gone()
        fragment_alarm.gone()
        fragment_settings.gone()

        return when (menuState) {
            MenuViewModel.MenuState.DREAMS_LIST -> {
                fragment_dreams_list.visible()
            }
            MenuViewModel.MenuState.ALARM -> {
                fragment_alarm.visible()
            }
            MenuViewModel.MenuState.SETTINGS -> {
                fragment_settings.visible()
            }
        }
    }

    private fun refreshActionBar() {
    }

    companion object {
        private const val FRAGMENT_TAG_DREAMS_LIST = "fragment.dreamsList"
        private const val FRAGMENT_TAG_ALARM = "fragment.alarm"
        private const val FRAGMENT_TAG_SETTINGS = "fragment.settings"
    }

}
