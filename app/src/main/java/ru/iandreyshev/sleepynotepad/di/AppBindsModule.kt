package ru.iandreyshev.sleepynotepad.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import ru.iandreyshev.sleepynotepad.application.SpeepyNoteApplication
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
abstract class AppBindsModule {

    @Binds
    @Singleton
    abstract fun provideContext(application: SpeepyNoteApplication): Application

}
