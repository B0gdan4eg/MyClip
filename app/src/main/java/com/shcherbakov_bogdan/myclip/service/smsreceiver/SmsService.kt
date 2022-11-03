package com.shcherbakov_bogdan.myclip.service.smsreceiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.shcherbakov_bogdan.myclip.data.MyDatabase
import com.shcherbakov_bogdan.myclip.data.sms.TransactionFromSms
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject


class SmsService : Service() {

    @Inject
    lateinit var database: MyDatabase

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val smsBody = intent!!.extras!!.getString("smsBody")
        if (smsBody != null) {
            showNotification(smsBody)
            processSms(smsBody)?.let { saveSms(it) }
        }
        return START_STICKY
    }

    private fun saveSms(processSms: TransactionFromSms) {
        database.smsDao().insert(processSms)
    }

    private fun showNotification(smsBody: String) {
        TODO("Not yet implemented")
    }

    private fun processSms(smsBody: String): TransactionFromSms? {
        val pattern: Pattern = Pattern.compile("Oplata([\\s\\S]+?)BYN")
        if (pattern.matcher(smsBody).find()) {
            val matcher: Matcher = pattern.matcher(smsBody)
            matcher.find()
            val str = matcher.group().split("")
            return TransactionFromSms(
                0,
                str[1].toDouble(),
                Calendar.getInstance().toString(),
                false,
                0
            )
        }
        return null
    }
}
//Priorbank. Karta 4***2841 19-10-2022 11:56:01.
//Oplata 2.01 BYN. BLR SHOP SOSEDI.
//Dostupno: 11.52 BYN. Spravka: 80172899292