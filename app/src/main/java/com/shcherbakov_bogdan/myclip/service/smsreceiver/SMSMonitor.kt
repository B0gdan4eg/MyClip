package com.shcherbakov_bogdan.myclip.service.smsreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class SMSMonitor : BroadcastReceiver() {
    private val ACTION = "android.provider.Telephony.SMS_RECEIVED"
    override fun onReceive(p0: Context?, p1: Intent?) {
        TODO("Not yet implemented")
    }

}