package com.shcherbakov_bogdan.myclip.service.smsreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import androidx.annotation.RequiresApi


class SMSMonitor : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent != null && intent.action != null && ACTION.compareTo(
                intent.action!!,
                ignoreCase = true
            ) == 0
        ) {
            val pduArray = intent.extras!!["pdus"] as Array<ByteArray>?
            val messages: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(pduArray!!.size)
            for (i in pduArray!!.indices) {
                messages[i] = SmsMessage.createFromPdu(pduArray[i],"")
            }
            val smsFrom: String = messages[0]!!.displayOriginatingAddress
            if (smsFrom.equals("RM FIGHT", ignoreCase = true)) {
                val bodyText = StringBuilder()
                for (i in messages.indices) {
                    bodyText.append(messages[i]!!.messageBody)
                }
                val body = bodyText.toString()
                val mIntent = Intent(context, SmsService::class.java)
                mIntent.putExtra("sms_body", body)
                context.startService(mIntent)
                abortBroadcast()
            }
        }
    }

    companion object {
        private const val ACTION = "android.provider.Telephony.SMS_RECEIVED"
    }
}