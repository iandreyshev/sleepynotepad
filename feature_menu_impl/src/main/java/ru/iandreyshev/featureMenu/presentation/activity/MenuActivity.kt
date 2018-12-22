package ru.iandreyshev.featureMenu.presentation.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_menu.*
import ru.iandreyshev.featureMenu.R
import ru.iandreyshev.featureMenu.di.FeatureMenuComponent
import ru.iandreyshev.featureMenu.viewModel.MenuViewModel
import ru.iandreyshev.coreui.ui.activity.BaseAppCompatActivity
import ru.iandreyshev.coreui.ui.view.setOnClickListener
import ru.iandreyshev.coreui.viewModel.observeNotNull
import ru.iandreyshev.featureDreamsApi.api.IDreamsDiaryFragmentFactory
import javax.inject.Inject

class MenuActivity : BaseAppCompatActivity() {

    companion object {
        private const val MAIN_FRAGMENT_TAG = "fragment.main"
    }

    @Inject
    lateinit var mMainFragmentFactory: IDreamsDiaryFragmentFactory

    private val mViewModel by lazy { viewModel<MenuViewModel>() }

    private val mMainFragment: Fragment?
        get() = supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        FeatureMenuComponent.get().inject(this)

        initActionBar()
        initMainFragment()
        initFabButton()

        subscribeToViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
            return
        } else if (mViewModel.menuState.value != MenuViewModel.MenuState.DREAMS) {
            mViewModel.onNewMenuState(MenuViewModel.MenuState.DREAMS)
            drawer.closeDrawer(GravityCompat.START)
            return
        }

        super.onBackPressed()
    }

    private fun subscribeToViewModel() {
        mViewModel.apply {
            observeNotNull(dreamsAvailability, ::handleDreamsAvailability)
        }
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun initMainFragment() {
        if (mMainFragment == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_placeholder, mMainFragmentFactory.create(), MAIN_FRAGMENT_TAG)
                    .commit()
        }
    }

    private fun initFabButton() {
        floating_action_button.setOnClickListener(mViewModel::onCreateDream)
    }

    private fun handleDreamsAvailability(isAvailable: Boolean) {
        refreshActionBar()
    }

    private fun refreshActionBar() {
    }

    private fun setupDreamsViewState() {
    }

    private fun setupSettingsViewState() {
    }

}
