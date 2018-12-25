package ru.iandreyshev.featureDreams.mapping

import ru.iandreyshev.featureDreams.storage.entity.DreamEntity
import ru.iandreyshev.featureDreamsApi.domain.DreamProperties

fun DreamProperties.toEntity(): DreamEntity = DreamEntity(
        title = title,
        description = description,
        date = sleepingDate,
        isLucid = isLucid
)