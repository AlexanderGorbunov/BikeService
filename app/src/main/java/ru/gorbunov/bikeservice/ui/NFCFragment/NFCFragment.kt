package ru.gorbunov.bikeservice.ui.NFCFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_nfc.*
import ru.gorbunov.bikeservice.Model.Bike
import ru.gorbunov.bikeservice.R
import ru.gorbunov.bikeservice.di.component.DaggerFragmentComponent
import ru.gorbunov.bikeservice.di.module.FragmentModule
import javax.inject.Inject

class NFCFragment:Fragment(), NFCContract.View {

    companion object {
        val TAG: String = "NFCFragment"
    }

    @Inject
    lateinit var presenter: NFCContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): NFCFragment {
        return NFCFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_nfc, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        presenter.initializeNFC(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val NFCComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        NFCComponent.inject(this)
    }

    override fun loadMessageSuccess(intent: Intent) {
        TODO("Not yet implemented")
    }

    override fun downloadBikeStatusSuccess(bike: Bike?) {
        this.tv_bike_status.text = bike?.status
        this.tv_bike_comment.text = bike?.comments
    }

    override fun downloadBikeStatusError(error: String?) {
        Toast.makeText(this.context, "Ошибка при получении данных: $error", Toast.LENGTH_LONG).show()

    }
}