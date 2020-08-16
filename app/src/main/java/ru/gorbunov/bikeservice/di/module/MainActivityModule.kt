package ru.gorbunov.bikeservice.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import ru.gorbunov.bikeservice.ui.main.MainContract
import ru.gorbunov.bikeservice.ui.main.MainPresenter

@Module
class MainActivityModule (private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }
}