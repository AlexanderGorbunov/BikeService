package ru.gorbunov.bikeservice.ui.main

import android.content.Context
import android.content.Intent
import ru.gorbunov.bikeservice.base.BaseContract

class MainContract {
    interface View: BaseContract.View {

        fun showApiScreen()
        fun showNFCScreen()

        fun showError(error: String)
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        //fun onDrawerOptionAboutClick()
        fun onNFCFragmentReady(context: Context)
        fun onNFCMessaging(context: Context, intent: Intent?)
    }
}