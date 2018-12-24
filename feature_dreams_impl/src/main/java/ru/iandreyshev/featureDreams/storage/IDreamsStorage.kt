package ru.iandreyshev.featureDreams.storage

import io.reactivex.Observable
import ru.iandreyshev.featureDreams.storage.entity.DreamEntity
import ru.iandreyshev.featureDreams.storage.result.StorageDreamsClearResult
import ru.iandreyshev.featureDreams.storage.result.StorageDreamsDeleteResult
import ru.iandreyshev.featureDreams.storage.result.StorageDreamsSaveResult
import ru.iandreyshev.featureDreamsApi.domain.DreamKey

interface IDreamsStorage {
    val dreams: Observable<List<DreamEntity>>
    fun save(dream: DreamEntity): StorageDreamsSaveResult
    fun delete(key: DreamKey): StorageDreamsDeleteResult
    fun clear(): StorageDreamsClearResult
}
