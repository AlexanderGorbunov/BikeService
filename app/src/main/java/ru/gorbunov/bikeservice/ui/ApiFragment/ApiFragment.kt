package ru.gorbunov.bikeservice.ui.ApiFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_api_screen.*
import ru.gorbunov.bikeservice.R
import ru.gorbunov.bikeservice.di.component.DaggerFragmentComponent
import ru.gorbunov.bikeservice.di.module.FragmentModule
import javax.inject.Inject

class ApiFragment : Fragment(), ApiContract.View {

    companion object {
        val TAG: String = "ApiFragment"
    }

    @Inject
    lateinit var presenter: ApiContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): ApiFragment {
        return ApiFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_api_screen, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        this.btn_apikey_confirm.setOnClickListener {
            presenter.saveApiKey(this, eT_apikey.text.toString())
        }

    }

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        aboutComponent.inject(this)
    }

    override fun saveApiKeyError() {
        Toast.makeText(this.context, "Введите Api-ключ", Toast.LENGTH_SHORT).show()
    }

    override fun saveApiKeySuccess() {
        Toast.makeText(this.context, "Api-ключ Введён!", Toast.LENGTH_SHORT).show()
    }

    override fun clearApiKey() {
        this.eT_apikey.setText("")
    }
}