package ru.gorbunov.bikeservice.ui.ApiFragment

import ru.gorbunov.bikeservice.base.BaseContract

class ApiContract {

    interface View: BaseContract.View {
        fun saveApiKeyError()
        fun saveApiKeySuccess()

        fun clearApiKey()
        //fun checkApiKey()
        //fun showProgress(show: Boolean)
        //fun loadMessageSuccess(message: String)
        // fun loadMessageError() // When it's a real request, this function should be implemented, too
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun saveApiKey(apiF : ApiFragment, key : String)
        fun checkApiKey(apiF : ApiFragment)
    }
}