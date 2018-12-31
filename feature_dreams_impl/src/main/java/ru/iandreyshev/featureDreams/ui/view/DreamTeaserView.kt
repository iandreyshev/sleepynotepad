package ru.iandreyshev.featureDreams.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_dream_teaser.view.*
import ru.iandreyshev.featureDreams.R
import ru.iandreyshev.vext.view.gone
import ru.iandreyshev.vext.view.visible
import ru.iandreyshev.vext.view.visibleIfOrGone

class DreamTeaserView
@JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.view_dream_teaser, this)
    }

    private fun measureTeaserLines() {
        val isMainTeaserExists = tvTextTeaserMain.text.isNotEmpty()

        tvTextTeaserMain.visibleIfOrGone(isMainTeaserExists)
        tvTextTeaserSecondary.visibleIfOrGone(tvTextTeaserSecondary.text.isNotEmpty())

        val freeLinesCount = if (isMainTeaserExists)
            MAIN_TEASER_MAX_LINES - tvTextTeaserMain.lineCount
        else SECONDARY_TEASER_MAX_LINES

        if (freeLinesCount > 0) {
            tvTextTeaserSecondary.maxLines = freeLinesCount
            tvTextTeaserSecondary.visible()
        } else {
            tvTextTeaserSecondary.maxLines = 0
            tvTextTeaserSecondary.gone()
        }
    }

    companion object {
        private const val MAIN_TEASER_MAX_LINES = 3
        private const val SECONDARY_TEASER_MAX_LINES = 3
    }

}
