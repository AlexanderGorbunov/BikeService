package ru.gorbunov.bikeservice.ui.ApiFragment

import android.widget.Toast
import ru.gorbunov.bikeservice.Model.Constants
import ru.gorbunov.bikeservice.ui.main.MainActivity

class ApiPresenter : ApiContract.Presenter{

    private lateinit var view: ApiContract.View

    override fun subscribe() {}

    override fun unsubscribe() {}

    override fun attach(view: ApiContract.View) {
        this.view = view
    }

    override fun saveApiKey(apiF : ApiFragment, key: String) {
        if (key == "")
            view.saveApiKeyError()
        else
        {
            Constants.Api_Key = key
            checkApiKey(apiF)
        }
    }

    override fun checkApiKey(apiF: ApiFragment) {
        if (Constants.Api_Key != "") {
            view.saveApiKeySuccess()
            view.clearApiKey()
            (apiF.activity as MainActivity).showNFCScreen()
        }
    }

}