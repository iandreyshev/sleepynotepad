package ru.iandreyshev.featureDreams.di

import dagger.Module
import dagger.Provides
import io.objectbox.Box
import io.objectbox.BoxStore
import ru.iandreyshev.coreUtils.di.context.IContextProvider
import ru.iandreyshev.coreUtils.di.scope.PerFeature
import ru.iandreyshev.featureDreams.storage.entity.DreamEntity
import ru.iandreyshev.featureDreams.storage.entity.MyObjectBox
import ru.iandreyshev.featureDreams.ui.fragment.DreamsListFragment
import ru.iandreyshev.featureDreamsApi.api.IDreamsListFragmentFactory

@Module
class SharedModuleWithProvides {

    @Provides
    @PerFeature
    fun provideBoxStore(contextProvider: IContextProvider): BoxStore =
            MyObjectBox.builder()
                    .androidContext(contextProvider.context)
                    .name("FeatureDreams")
                    .build()

    @Provides
    @PerFeature
    fun provideDreamsBox(boxStore: BoxStore): Box<DreamEntity> =
            boxStore.boxFor(DreamEntity::class.java)

    @Provides
    @PerFeature
    fun provideDreamsDiaryFragmentFactory(): IDreamsListFragmentFactory {
        return object : IDreamsListFragmentFactory {
            override fun create() = DreamsListFragment()
        }
    }

}
