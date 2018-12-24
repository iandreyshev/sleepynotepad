package ru.iandreyshev.featureDreams.storage.impl

import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import ru.iandreyshev.coreUtils.rx.ioToMain
import ru.iandreyshev.featureDreams.storage.IDreamsStorage
import ru.iandreyshev.featureDreams.storage.entity.DreamEntity
import ru.iandreyshev.featureDreams.storage.entity.DreamEntity_
import ru.iandreyshev.featureDreams.storage.result.StorageDreamsClearResult
import ru.iandreyshev.featureDreams.storage.result.StorageDreamsDeleteResult
import ru.iandreyshev.featureDreams.storage.result.StorageDreamsSaveResult
import ru.iandreyshev.featureDreamsApi.domain.DreamKey
import javax.inject.Inject

class DreamsStorage
@Inject constructor(
    private val dreamsBox: Box<DreamEntity>
) : IDreamsStorage {

    override val dreams: Observable<List<DreamEntity>>
        get() = RxQuery.observable(dreamsBox.query().notNull(DreamEntity_.__ID_PROPERTY).build())
            .ioToMain()

    override fun save(dream: DreamEntity): StorageDreamsSaveResult {
        dreamsBox.all
        dreamsBox.put(dream)

        // TODO
        return StorageDreamsSaveResult.SUCCESS
    }

    override fun delete(key: DreamKey): StorageDreamsDeleteResult {
        dreamsBox.remove(key.id)

        // TODO
        return StorageDreamsDeleteResult.SUCCESS
    }

    override fun clear(): StorageDreamsClearResult {
        dreamsBox.removeAll()

        // TODO
        return StorageDreamsClearResult.SUCCESS
    }

}