package ru.iandreyshev.featureDreams.ui.adapter.dreams

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.view_dream_teaser.view.*
import ru.iandreyshev.featureDreams.R
import ru.iandreyshev.featureDreams.ui.extension.dateViewString
import ru.iandreyshev.featureDreamsApi.domain.Dream
import ru.iandreyshev.featureDreamsApi.domain.SleepingDate
import ru.iandreyshev.vext.view.visibleIfOrGone

class DreamListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var mItem: Dream

    fun bindItem(item: Dream): DreamListViewHolder {
        mItem = item

        bindDescription(item.properties.isLucid)
        bindTeaser(item.properties.title, item.properties.description)
        bindDate(item.properties.sleepingDate)

        return this
    }

    fun onClick(action: (View, Dream) -> Unit): DreamListViewHolder {
        itemView.setOnClickListener { action(itemView, mItem) }
        return this
    }

    fun onLongClick(action: (Dream) -> Boolean): DreamListViewHolder {
        itemView.setOnLongClickListener { action(mItem) }
        return this
    }

    private fun bindDescription(isLucid: Boolean) {
        itemView.tvDescription.visibleIfOrGone(isLucid)

        if (isLucid) {
            itemView.tvDescription.text =
                    itemView.context.getString(R.string.dreams_diary_description_lucid)
        }
    }

    private fun bindTeaser(title: String?, text: String) {
        title?.run {
            itemView.tvTextTeaserMain.text = title
        }
        if (text.isNotBlank()) {
            itemView.tvTextTeaserSecondary.text = text
        }
    }

    private fun bindDate(sleepingDate: SleepingDate) {
        itemView.tvDate.text = sleepingDate.dateViewString
    }

    companion object {
        private const val MAIN_TEASER_MAX_LINES = 3
    }

}
