package ru.iandreyshev.featureDreams.ui.fragment

import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.fragment_dream.*
import ru.iandreyshev.coreui.ui.dialog.buildAlert
import ru.iandreyshev.coreui.ui.dialog.customizeAndShow
import ru.iandreyshev.coreui.ui.fragment.BaseFragment
import ru.iandreyshev.featureDreams.R
import ru.iandreyshev.featureDreams.di.FeatureDreamsComponent
import ru.iandreyshev.featureDreams.domain.DeleteResult
import ru.iandreyshev.featureDreams.ui.extension.dateViewString
import ru.iandreyshev.featureDreams.viewModel.DreamViewModel
import ru.iandreyshev.featureDreams.viewModel.IViewModelFactory
import ru.iandreyshev.featureDreamsApi.domain.Dream
import javax.inject.Inject

class DreamFragment : BaseFragment() {

    @Inject
    lateinit var mViewModelFactory: IViewModelFactory

    private lateinit var mViewModel: DreamViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_dream, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FeatureDreamsComponent.get().inject(this)

        initActionBar()

//        val bundleWithDream = intent.extras?.getBundle(KEY_DREAM)
//
//        mViewModel = mViewModelFactory.dreamViewModel(this, bundleWithDream).apply {
//            observeNotNull(dream, ::handleDreamProperties)
////            observeNotNull(deleteDreamWaiting, progressBar::visibleIfOrGone)
//            observeNotNull(deletingResultEvent, ::handleDeleteDreamResult)
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId) {
//            android.R.id.home -> supportFinishAfterTransition()
//            R.id.menu_item_edit -> startEditActivity()
//            R.id.menu_item_delete -> showDeletePopup()
//            else -> return super.onOptionsItemSelected(item)
//        }

        return true
    }

    private fun initActionBar() {
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun handleDreamProperties(dream: Dream) {
        tvDescription.text = dream.properties.description
        tvDate.text = dream.dateViewString
    }

    private fun handleDeleteDreamResult(result: DeleteResult) {
//        if (result == DeleteResult.SUCCESS) {
//            finish()
//            return
//        }
//
//        buildAlert {
//            titleResource = R.string.delete_error_title
//            messageResource = when (result) {
//                DeleteResult.SUCCESS -> throw IllegalStateException()
//                DeleteResult.ERROR_NO_CONNECTION -> R.string.delete_error_no_connection
//                DeleteResult.ERROR_UNDEFINED -> R.string.delete_error_undefined
//            }
////            okButton { }
//        } customizeAndShow {}
    }

    private fun startEditActivity() {
        mViewModel.dream.value?.toBundle()?.let { dreamBundle ->
//            intentFor<DreamEditorActivity>()
//                    .putExtra(DreamEditorActivity.KEY_DREAM, dreamBundle)
//                    .let(::startActivity)
        }
    }

    private fun showDeletePopup() {
        buildAlert {
            titleResource = R.string.dream_delete_popup_title
            messageResource = R.string.dream_delete_popup_message
//            okButton { mViewModel.delete() }
//            noButton { }
        } customizeAndShow {
            setCanceledOnTouchOutside(true)
            setCancelable(false)
        }
    }

    companion object {
        const val KEY_DREAM = "key_list_item"
    }

}
