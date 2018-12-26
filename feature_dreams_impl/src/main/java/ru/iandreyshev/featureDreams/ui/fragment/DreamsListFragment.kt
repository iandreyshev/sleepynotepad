package ru.iandreyshev.featureDreams.ui.fragment

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dreams_list.*
import kotlinx.android.synthetic.main.view_dream_teaser.view.*
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import ru.iandreyshev.featureDreams.R
import ru.iandreyshev.featureDreams.di.FeatureDreamsComponent
import ru.iandreyshev.featureDreams.ui.adapter.dreams.DreamsListAdapter
import ru.iandreyshev.coreui.ui.dialog.buildAlert
import ru.iandreyshev.coreui.ui.dialog.customizeAndShow
import ru.iandreyshev.coreui.ui.fragment.BaseFragment
import ru.iandreyshev.coreui.viewModel.observeNotNull
import ru.iandreyshev.featureDreams.domain.FetchDreamsResult
import ru.iandreyshev.featureDreams.ui.activity.DreamEditorActivity
import ru.iandreyshev.featureDreams.viewModel.DreamListViewModel
import ru.iandreyshev.featureDreamsApi.domain.Dream
import ru.iandreyshev.vext.view.goneIfOrVisible
import ru.iandreyshev.vext.view.visibleIfOrGone

class DreamsListFragment : BaseFragment() {

    private val mViewModel by lazy { viewModel<DreamListViewModel>() }
    private val mDreamsAdapter by lazy { DreamsListAdapter(DreamActionListener()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_dreams_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FeatureDreamsComponent.get().inject(this)

        initDreamsList()
        initAddFirstButton()
        initAddButton()

        mViewModel.apply {
            observeNotNull(dreams, ::handleDreams)
            observeNotNull(optionsTarget, ::handleOptionsTarget)
            observeNotNull(fetchResult, ::handleFetchResult)
        }
    }

    private fun initDreamsList() {
        dreamsList.adapter = mDreamsAdapter
    }

    private fun initAddFirstButton() {
        btnAddFirst.setOnClickListener {
            startActivity<DreamEditorActivity>()
        }
    }

    private fun initAddButton() {
        btnAdd.setOnClickListener {
            startActivity<DreamEditorActivity>()
        }
    }

    private fun handleDreams(dreams: List<Dream>) {
        val isAvailable = dreams.isNotEmpty()
        btnAdd.visibleIfOrGone(isAvailable)
        emptyView.goneIfOrVisible(isAvailable)
        dreamsList.visibleIfOrGone(isAvailable)

        mDreamsAdapter.dreams = dreams
        mDreamsAdapter.notifyDataSetChanged()
    }

    private fun handleOptionsTarget(target: Dream) {
        buildAlert {
            title = "Dream options"
            onCancelled { mViewModel.onCloseDreamOptions() }
        } customizeAndShow {
            setCancelable(false)
            setCanceledOnTouchOutside(true)
        }
    }

    private fun handleFetchResult(result: FetchDreamsResult) {
        toast(when (result) {
            FetchDreamsResult.SUCCESS -> return
            FetchDreamsResult.ERROR_NO_CONNECTION -> R.string.fetch_error_no_connection
            FetchDreamsResult.ERROR_UNDEFINED -> R.string.fetch_error_undefined
        })
    }

    private inner class DreamActionListener : DreamsListAdapter.IDreamActionListener {
        override fun onClick(view: View, dream: Dream) {
            val intent = intentFor<DreamFragment>()
            intent.putExtra(DreamFragment.KEY_DREAM, dream.toBundle())

            val sharedDescription = Pair.create(view.tvDescription as View, "trans_key_dream_description")
            val sharedDate = Pair.create(view.tvDate as View, "trans_key_dream_date")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity ?: return, sharedDescription, sharedDate)

            startActivity(intent, options.toBundle())
        }

        override fun onLongClick(dream: Dream): Boolean {
//            mViewModel.onOpenDreamOptions(dream)
            return true
        }
    }

}
