package ru.iandreyshev.featureDreams.ui.extension

import ru.iandreyshev.featureDreamsApi.domain.Dream
import ru.iandreyshev.featureDreamsApi.domain.DreamProperties
import ru.iandreyshev.featureDreamsApi.domain.SleepingDate
import java.text.SimpleDateFormat

val Dream.dateViewString: String
    get() = properties.dateViewString

val DreamProperties.dateViewString: String
    get() = sleepingDate.dateViewString

val SleepingDate.dateViewString: String
    get() = SimpleDateFormat("dd.MM.yyyy").format(timesTemp)
