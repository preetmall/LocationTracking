package com.example.trackingtest.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.trackingtest.LocationService
import com.example.trackingtest.R
import kotlinx.android.synthetic.main.fragment_location_details.*


class LocationDetailsFragment : Fragment() {
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

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                var location: Location = intent?.getParcelableExtra<Location>("location")!!
                currentLN.setText("${location.latitude}  ${location.longitude}")
            }
        }
        requireContext().registerReceiver(
            broadcastReceiver,
            IntentFilter("UPDATE_LOCATION")
        )
        ContextCompat.startForegroundService(
            requireContext(),
            Intent(requireContext(), LocationService::class.java)
        )

    }

    companion object {
        @JvmStatic
        fun newInstance() = LocationDetailsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(broadcastReceiver)
    }


}
