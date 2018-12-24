package ru.iandreyshev.sleepynotepad.proxy.navigation

import android.app.Application
import org.jetbrains.anko.*
import ru.iandreyshev.featureDreams.ui.activity.DreamEditorActivity
import ru.iandreyshev.featureMenu.di.dependencies.IMenuNavigator
import javax.inject.Inject

class FeatureMenuMenuNavigator
@Inject constructor(
        private val application: Application
) : IMenuNavigator {

    override fun onCreateDream() {
        application.startActivity<DreamEditorActivity>()
    }

}
