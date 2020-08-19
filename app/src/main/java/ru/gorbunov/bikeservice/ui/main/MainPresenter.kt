package ru.gorbunov.bikeservice.ui.main

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import ru.gorbunov.bikeservice.Model.SubUtils
import ru.gorbunov.bikeservice.ui.NFCFragment.NFCFragment


class MainPresenter: MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View
    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pendingIntent: PendingIntent

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showApiScreen() // as default
    }

    override fun onNFCFragmentReady(context: Context)
    {
        //Toast.makeText(context, "onNFCFragmentReady", Toast.LENGTH_SHORT).show()
        nfcAdapter = NfcAdapter.getDefaultAdapter(context)
        if (nfcAdapter != null) {
            enableNFCDispatching(true, context)
        }
        else
        {
            view.showError("Необходимо устройство с NFC")
        }
    }

    override fun onNFCMessaging(context: Context, intent: Intent?) {
        if (intent!!.action != null) {
            val mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG) as Tag
            Log.d("tag", SubUtils.toHex(mTag.id))
            //Toast.makeText(context, String(mTag.id), Toast.LENGTH_SHORT).show()
            if ((context as MainActivity).supportFragmentManager.findFragmentByTag(NFCFragment.TAG) != null) {
                (context.supportFragmentManager.findFragmentByTag(NFCFragment.TAG) as NFCFragment).presenter.loadDataAll(String(mTag.id))
            }
        }
    }

    override fun enableNFCDispatching(flag: Boolean, context: Context) {
        if (flag && nfcAdapter != null)
        {
            pendingIntent = PendingIntent.getActivity(
                context, 0,
                Intent(context, context.javaClass)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
            )

            val filters = arrayOfNulls<IntentFilter>(1)
            filters[0] = IntentFilter()
            filters[0]!!.addAction(NfcAdapter.ACTION_TAG_DISCOVERED)
            filters[0]!!.addCategory(Intent.CATEGORY_DEFAULT)
            nfcAdapter.enableForegroundDispatch(
                context as MainActivity,
                pendingIntent,
                filters,
                null
            )
        }
        else
        {
            if (nfcAdapter != null)
            {
                nfcAdapter.disableForegroundDispatch(context as MainActivity);
            }
        }
    }

    /*private fun showWirelessSettings() {
        Toast.makeText(this, "You need to enable NFC", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        startActivity(intent)
    }*/

    /*override fun onDrawerOptionAboutClick() {
        view.showAboutFragment()
    }*/
}