package ru.iandreyshev.sleepynotepad.di

import dagger.Component
import dagger.android.AndroidInjector
import ru.iandreyshev.sleepynotepad.application.SpeepyNoteApplication
import javax.inject.Singleton

@Component(
        modules = [
            AppBindsModule::class
        ]
)
@Singleton
interface AppComponent : AndroidInjector<SpeepyNoteApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<SpeepyNoteApplication>()

}
