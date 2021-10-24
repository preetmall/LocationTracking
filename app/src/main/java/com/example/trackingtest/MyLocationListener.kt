package com.example.trackingtest

import android.location.Location

interface MyLocationListener {
    fun onLocationChanged(location: Location?)
}