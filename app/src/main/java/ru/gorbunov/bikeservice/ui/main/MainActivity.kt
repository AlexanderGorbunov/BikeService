package ru.gorbunov.bikeservice.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.gorbunov.bikeservice.R
import ru.gorbunov.bikeservice.di.component.DaggerMainActivityComponent
import ru.gorbunov.bikeservice.di.module.MainActivityModule
import ru.gorbunov.bikeservice.ui.ApiFragment.ApiFragment
import ru.gorbunov.bikeservice.ui.NFCFragment.NFCFragment
import javax.inject.Inject


class MainActivity : AppCompatActivity() , MainContract.View{

    @Inject
    lateinit var presenter: MainContract.Presenter

    var isNFCFra : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)

    }

    private fun injectDependency() {
        val activityComponent = DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    override fun showApiScreen() {
        if (supportFragmentManager.findFragmentByTag(ApiFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.main_frame, ApiFragment().newInstance(), ApiFragment.TAG)
                .commit()
            isNFCFra = false
        } else {}
    }

    override fun showNFCScreen() {
        if (supportFragmentManager.findFragmentByTag(NFCFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_frame, NFCFragment().newInstance(), NFCFragment.TAG)
                .commit()
            isNFCFra = true
        } else {}
    }

    override fun showError(error: String) {
        Toast.makeText(this, "Произошла ошибка: $error", Toast.LENGTH_SHORT).show()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        //Toast.makeText(this, "onNewIntent", Toast.LENGTH_SHORT).show()
        presenter.onNFCMessaging(this, intent)
    }

    override fun onPause() {
        super.onPause()
        //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
        presenter.enableNFCDispatching(false, this)
    }
    override fun onResume() {
        super.onResume()
        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
        if (isNFCFra)
            presenter.enableNFCDispatching(true, this)
    }
}