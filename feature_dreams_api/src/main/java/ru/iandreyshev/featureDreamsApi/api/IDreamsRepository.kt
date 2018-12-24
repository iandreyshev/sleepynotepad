package ru.iandreyshev.featureDreamsApi.api

import io.reactivex.Observable
import ru.iandreyshev.featureDreamsApi.domain.Dream
import ru.iandreyshev.featureDreamsApi.domain.DreamKey
import ru.iandreyshev.featureDreamsApi.domain.LoadDreamResult

interface IDreamsRepository {
    val dreams: Observable<List<Dream>>
    fun getDream(key: DreamKey): Observable<LoadDreamResult>
}
