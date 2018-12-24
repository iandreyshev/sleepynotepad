package ru.iandreyshev.sleepynotepad.proxy.navigation

import android.app.Application
import org.jetbrains.anko.startActivity
import ru.iandreyshev.featureMenu.di.dependencies.ISplashNavigator
import ru.iandreyshev.featureMenu.ui.activity.MenuActivity
import javax.inject.Inject

class FeatureMenuSplashNavigator
@Inject constructor(
        private val application: Application
) : ISplashNavigator {

    override fun onCompleted() {
        application.startActivity<MenuActivity>()
    }

}