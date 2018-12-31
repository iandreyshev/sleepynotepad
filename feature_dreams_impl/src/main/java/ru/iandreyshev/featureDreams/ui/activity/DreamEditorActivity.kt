package ru.iandreyshev.featureDreams.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_dream_editor.*
import org.jetbrains.anko.cancelButton
import ru.iandreyshev.coreui.ui.activity.BaseAppCompatActivity
import ru.iandreyshev.coreui.ui.dialog.buildAlert
import ru.iandreyshev.coreui.ui.dialog.customizeAndShow
import ru.iandreyshev.coreui.viewModel.observeNotNull
import ru.iandreyshev.featureDreams.R
import ru.iandreyshev.featureDreams.di.FeatureDreamsComponent
import ru.iandreyshev.featureDreams.domain.SaveResult
import ru.iandreyshev.featureDreamsApi.domain.DreamProperties
import ru.iandreyshev.featureDreams.viewModel.DreamEditorViewModel
import ru.iandreyshev.featureDreams.viewModel.IViewModelFactory
import ru.iandreyshev.featureDreamsApi.domain.Dream
import ru.iandreyshev.featureDreamsApi.domain.SleepingDate
import ru.iandreyshev.vext.view.visibleIfOrGone
import java.lang.IllegalStateException
import javax.inject.Inject

class DreamEditorActivity : BaseAppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: IViewModelFactory

    private lateinit var mViewModel: DreamEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dream_editor)

        FeatureDreamsComponent.get().inject(this)

        initActionBar()

        mViewModel = mViewModelFactory.dreamEditorViewModel(this, intent.extras).apply {
            observeNotNull(dream, ::handleDream)
            observeNotNull(saveResult, ::handleSaveResult)
            observeNotNull(saveWaiting, ::handleSaveWaiting)
            observeNotNull(closeEvent, ::finish)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_dream_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_item_save -> {
                mViewModel.saveDream(createDream())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
    }

    private fun handleDream(dream: Dream) {
        tvDreamText.setText(dream.properties.description)
    }

    private fun handleSaveResult(result: SaveResult) {
        if (result == SaveResult.SUCCESS) {
            return
        }

        buildAlert {
            titleResource = R.string.save_error_title
            messageResource = when (result) {
                SaveResult.SUCCESS -> throw IllegalStateException()
                SaveResult.ERROR_EMPTY_DREAM,
                SaveResult.ERROR_BLANK_DREAM -> R.string.save_error_empty
                SaveResult.ERROR_LARGE_DREAM -> R.string.save_error_large
                SaveResult.ERROR_NO_CONNECTION -> R.string.save_error_no_connection
                SaveResult.ERROR_UNDEFINED -> R.string.save_error_undefined
            }
            cancelButton { mViewModel.onCloseSaveResult() }
        } customizeAndShow {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    private fun handleSaveWaiting(isWait: Boolean) {
        progressBar.visibleIfOrGone(isWait)
    }

    private fun createDream(): DreamProperties {
        return DreamProperties(
                title = dream_title.text.toString(),
                description = tvDreamText.text.toString(),
                sleepingDate = SleepingDate(System.currentTimeMillis()),
                isLucid = dream_is_lucid.isChecked
        )
    }

    companion object {
        const val KEY_DREAM = "key_dream_to_edit"
    }

}