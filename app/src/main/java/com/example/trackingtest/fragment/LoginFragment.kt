package com.example.trackingtest.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trackingtest.PrefKey
import com.example.trackingtest.R
import kotlinx.android.synthetic.main.fragment_login.*
import replaceFragment

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBN.setOnClickListener {
            val sharedPreference = requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference?.edit()
            editor?.putString(PrefKey.USER_NAME,nameET.text.toString())
            editor?.putString(PrefKey.PHONE,phoneNumberET.text.toString())
            editor?.commit()
            val locationDetailsFragment:LocationDetailsFragment=LocationDetailsFragment.newInstance()
            replaceFragment(requireActivity().supportFragmentManager, locationDetailsFragment, R.id.main_fragment)

        }


    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}