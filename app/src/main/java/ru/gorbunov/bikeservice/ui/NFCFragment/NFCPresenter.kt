package ru.gorbunov.bikeservice.ui.NFCFragment

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.gorbunov.bikeservice.ApiService
import ru.gorbunov.bikeservice.Model.Bike
import ru.gorbunov.bikeservice.Model.Constants
import ru.gorbunov.bikeservice.Model.SubUtils.normalizeNFCCode
import ru.gorbunov.bikeservice.ui.main.MainActivity

class NFCPresenter: NFCContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiService = ApiService.create()

    private lateinit var view: NFCContract.View

    override fun subscribe() {}
    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: NFCContract.View) {
        this.view = view
    }

    override fun initializeNFC(nfcF: NFCFragment) {
        (nfcF.activity as MainActivity).presenter.onNFCFragmentReady(nfcF.activity as MainActivity)
    }

    override fun loadDataAll(code: String) {
        val subscribe = api.getBikeStatus(normalizeNFCCode(code), Constants.Api_Key).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                bike : Bike? ->
                view.downloadBikeStatusSuccess(bike)
            }, {error ->
                view.downloadBikeStatusError(error.localizedMessage)
        })
        subscriptions.add(subscribe)
    }
}