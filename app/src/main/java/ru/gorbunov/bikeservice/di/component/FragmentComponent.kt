package ru.gorbunov.bikeservice.di.component

import dagger.Component
import ru.gorbunov.bikeservice.di.module.FragmentModule
import ru.gorbunov.bikeservice.ui.ApiFragment.ApiFragment
import ru.gorbunov.bikeservice.ui.NFCFragment.NFCFragment

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(apiFragment: ApiFragment)

    fun inject(NFCFragment: NFCFragment)

}