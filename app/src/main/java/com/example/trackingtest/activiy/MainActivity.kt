package com.example.trackingtest.activiy

import addFragment
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.trackingtest.PrefKey
import com.example.trackingtest.R
import com.example.trackingtest.fragment.LocationDetailsFragment
import com.example.trackingtest.fragment.LoginFragment
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    var PERMISSIONS = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!checkPermission()) {
            requestPermission()
        }
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        if(sharedPreference.getString(PrefKey.PHONE,"").equals("")) {
            var selectedFragment: LoginFragment = LoginFragment.newInstance()
            addFragment(supportFragmentManager, selectedFragment, R.id.main_fragment)
        }else{
            val locationDetailsFragment:LocationDetailsFragment=LocationDetailsFragment.newInstance()
            addFragment(supportFragmentManager, locationDetailsFragment, R.id.main_fragment)
        }
    }
    fun checkPermission(): Boolean {
        if (applicationContext != null && PERMISSIONS != null) {
            for (permission in PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        permission!!
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, 1)
    }
}