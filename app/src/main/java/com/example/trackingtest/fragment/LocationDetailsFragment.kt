package com.example.trackingtest.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.trackingtest.LocationService
import com.example.trackingtest.MyLocationListener
import com.example.trackingtest.R
import kotlinx.android.synthetic.main.fragment_location_details.*


class LocationDetailsFragment : Fragment(), MyLocationListener {
    private var broadcastReceiver: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ContextCompat.startForegroundService(requireContext(), Intent(requireContext(), LocationService::class.java))

    }

    companion object {
        @JvmStatic
        fun newInstance() = LocationDetailsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(broadcastReceiver)
    }

    override fun onLocationChanged(location: Location?) {
        Log.d("ssds","${location?.latitude}  ${location?.longitude}")
        currentLN.setText("${location?.latitude}  ${location?.longitude}")
    }


}
