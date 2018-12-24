package ru.iandreyshev.featureDreams.di

import dagger.Component
import ru.iandreyshev.coreUtils.di.context.IContextProvider
import ru.iandreyshev.coreUtils.di.scope.PerFeature
import ru.iandreyshev.coreui.ui.activity.BaseAppCompatActivity
import ru.iandreyshev.coreui.ui.fragment.BaseFragment
import ru.iandreyshev.featureDreams.di.dependencies.IFeatureDreamsDependencies
import ru.iandreyshev.featureDreams.ui.fragment.DreamFragment
import ru.iandreyshev.featureDreams.ui.activity.DreamEditorActivity
import ru.iandreyshev.featureDreams.viewModel.DreamEditorViewModel
import ru.iandreyshev.featureDreams.viewModel.DreamListViewModel
import ru.iandreyshev.featureDreamsApi.api.IFeatureDreamsApi

@Component(
        modules = [
            SharedModuleWithProvides::class,
            SharedModuleWithBinds::class,
            ViewModelModule::class,
            UseCaseModule::class],
        dependencies = [
            IFeatureDreamsDependencies::class]
)
@PerFeature
abstract class FeatureDreamsComponent : IFeatureDreamsApi {

    abstract fun inject(activity: BaseAppCompatActivity)
    abstract fun inject(fragment: DreamFragment)
    abstract fun inject(activity: DreamEditorActivity)
    abstract fun inject(fragment: BaseFragment)
    abstract fun inject(viewModel: DreamEditorViewModel)
    abstract fun inject(viewModel: DreamListViewModel)

    @Component(dependencies = [
        IContextProvider::class]
    )
    abstract class DependenciesComponent : IFeatureDreamsDependencies

    companion object {
        fun init(component: FeatureDreamsComponent) {
            sInstance = component
        }

        fun get() = sInstance

        @Volatile
        private lateinit var sInstance: FeatureDreamsComponent
    }

}
