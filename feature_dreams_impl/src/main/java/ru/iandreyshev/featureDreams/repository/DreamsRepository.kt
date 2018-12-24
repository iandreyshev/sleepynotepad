package ru.iandreyshev.featureDreams.repository

import io.reactivex.Observable
import ru.iandreyshev.coreUtils.rx.ioToMain
import ru.iandreyshev.featureDreams.storage.IDreamsStorage
import ru.iandreyshev.featureDreamsApi.api.IDreamsRepository
import ru.iandreyshev.featureDreamsApi.domain.*
import javax.inject.Inject

class DreamsRepository
@Inject constructor(
        private val storage: IDreamsStorage
) : IDreamsRepository {

    override val dreams: Observable<List<Dream>>
        get() = storage.dreams
                .ioToMain()
                .map { list ->
                    list.map { entity ->
                        val properties = DreamProperties(entity.description)
                        val key = DreamKey(entity.id)
                        Dream(key, properties)
                    }
                }

    override fun getDream(key: DreamKey): Observable<LoadDreamResult> {
        return Observable.create {  }
    }

}
