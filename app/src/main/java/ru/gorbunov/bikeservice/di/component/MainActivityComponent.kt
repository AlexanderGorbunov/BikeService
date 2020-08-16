package ru.gorbunov.bikeservice.di.component

import dagger.Component
import ru.gorbunov.bikeservice.di.module.MainActivityModule
import ru.gorbunov.bikeservice.ui.main.MainActivity

@Component(modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}