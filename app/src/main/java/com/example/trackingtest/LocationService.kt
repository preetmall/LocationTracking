package com.example.trackingtest

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import cretaeFile
import java.util.*


class LocationService : Service() {
    override fun onCreate() {
        super.onCreate()

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, "1001")
                .setOngoing(false)
                .setSmallIcon(R.drawable.ic_menu_compass)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(
                "1001",
                "1001", NotificationManager.IMPORTANCE_LOW
            )
            notificationChannel.description = "1001"
            notificationManager.createNotificationChannel(notificationChannel)
            startForeground(1, builder.build())
        }

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val timer = Timer()
        //update location
        LocationHelper().startListeningUserLocation(
            this, object : MyLocationListener {
                override fun onLocationChanged(location: Location?) {
                    Handler(Looper.getMainLooper()).post(Runnable {
                        Log.d("kkk", "${location?.latitude} \t  ${location?.longitude}")
                        //set location in file
                        location?.let { cretaeFile(it) }

                    })
                }
            })
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}