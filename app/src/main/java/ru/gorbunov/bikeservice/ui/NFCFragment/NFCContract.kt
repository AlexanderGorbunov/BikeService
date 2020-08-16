package ru.gorbunov.bikeservice.ui.NFCFragment

import android.content.Intent
import ru.gorbunov.bikeservice.Model.Bike
import ru.gorbunov.bikeservice.base.BaseContract

class NFCContract {

    interface View: BaseContract.View {
        fun loadMessageSuccess(intent : Intent)
        fun downloadBikeStatusSuccess(bike : Bike?)
        fun downloadBikeStatusError(error: String?)

        /*fun showProgress(show: Boolean)
        fun loadMessageSuccess(message: String)*/
        // fun loadMessageError() // When it's a real request, this function should be implemented, too
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun initializeNFC(nfcF : NFCFragment)
        fun loadDataAll(code: String)
        //fun loadMessage() // Let's assume that this will be a retrofit request
    }

}