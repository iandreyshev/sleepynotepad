package ru.iandreyshev.featureDreamsApi.domain

import android.os.Bundle

data class DreamProperties(
        val title: String?,
        val description: String,
        val sleepingDate: SleepingDate,
        val isLucid: Boolean
) {

    fun toBundle(): Bundle {
        return Bundle().apply {
            putString(KEY_DESCRIPTION, description)
        }
    }

    companion object {
        private const val KEY_TITLE = "key_title"
        private const val KEY_DESCRIPTION = "key_description"
        private const val KEY_SLEEPING_DATE = "key_sleeping_date"
        private const val KEY_IS_LUCID = "key_is_lucid"

        fun create(bundle: Bundle?): DreamProperties? {
            bundle ?: return null

            if (!bundle.containsKey(KEY_SLEEPING_DATE)) {
                return null
            }
            val sleepingDate = bundle.getLong(KEY_SLEEPING_DATE)

            if (!bundle.containsKey(KEY_IS_LUCID)) {
                return null
            }
            val isLucid = bundle.getBoolean(KEY_IS_LUCID)

            return DreamProperties(
                    title = bundle.getString(KEY_TITLE),
                    description = bundle.getString(KEY_DESCRIPTION) ?: return null,
                    sleepingDate = SleepingDate(sleepingDate),
                    isLucid = isLucid)
        }
    }

}
