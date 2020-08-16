package ru.gorbunov.bikeservice.di.module

import dagger.Module
import dagger.Provides
import ru.gorbunov.bikeservice.ApiService
import ru.gorbunov.bikeservice.ui.ApiFragment.ApiContract
import ru.gorbunov.bikeservice.ui.ApiFragment.ApiPresenter
import ru.gorbunov.bikeservice.ui.NFCFragment.NFCContract
import ru.gorbunov.bikeservice.ui.NFCFragment.NFCPresenter

@Module
class FragmentModule {

    @Provides
    fun provideApiPresenter(): ApiContract.Presenter {
        return ApiPresenter()
    }

    @Provides
    fun provideNFCPresenter(): NFCContract.Presenter {
        return NFCPresenter()
    }

    @Provides
    fun provideApiService(): ApiService {
        return ApiService.create()
    }
}