package ru.iandreyshev.featureDreams.storage.entity

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import ru.iandreyshev.featureDreams.storage.converter.SleepingDateConverter
import ru.iandreyshev.featureDreamsApi.domain.SleepingDate

@Entity
data class DreamEntity(
        @Id(assignable = true)
        var id: Long = 0,
        var title: String? = null,
        var description: String = "",
        @Convert(converter = SleepingDateConverter::class, dbType = Long::class)
        var date: SleepingDate = SleepingDate(System.currentTimeMillis()),
        var isLucid: Boolean = false
)
