package com.droidlens

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment(R.layout.fragment_info) {

    private lateinit var textAndroidVersion: TextView
    private lateinit var textDeviceModel: TextView
    private lateinit var textManufacturer: TextView
    private lateinit var textKernelVersion: TextView
    private lateinit var textBatteryLevel: TextView
    private lateinit var textNetworkStatus: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textAndroidVersion = view.findViewById(R.id.textAndroidVersion)
        textDeviceModel = view.findViewById(R.id.textDeviceModel)
        textManufacturer = view.findViewById(R.id.textManufacturer)
        textKernelVersion = view.findViewById(R.id.textKernelVersion)
        textBatteryLevel = view.findViewById(R.id.textBatteryLevel)
        textNetworkStatus = view.findViewById(R.id.textNetworkStatus)
        updateDeviceInfo()
    }

    override fun onResume() {
        super.onResume()
        updateDeviceInfo()
    }

    private fun updateDeviceInfo() {
        val context = requireContext()

        textAndroidVersion.text = getString(
            R.string.value_android_version,
            Build.VERSION.RELEASE,
            Build.VERSION.SDK_INT
        )
        textDeviceModel.text = Build.MODEL
        textManufacturer.text = getString(
            R.string.value_manufacturer_brand,
            Build.MANUFACTURER,
            Build.BRAND
        )

        val kernel = System.getProperty("os.version") ?: getString(R.string.value_unknown)
        textKernelVersion.text = kernel

        textBatteryLevel.text = getBatteryLevel(context)
        textNetworkStatus.text = getNetworkStatus(context)
    }

    private fun getBatteryLevel(context: Context): String {
        val batteryIntent = context.registerReceiver(
            null,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )
        val level = batteryIntent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryIntent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val percent = if (level >= 0 && scale > 0) (level * 100) / scale else null
        return if (percent != null) {
            getString(R.string.value_battery_percent, percent)
        } else {
            getString(R.string.value_unknown)
        }
    }

    private fun getNetworkStatus(context: Context): String {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return getString(R.string.network_offline)
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return when {
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true ->
                getString(R.string.network_wifi)
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true ->
                getString(R.string.network_cellular)
            else -> getString(R.string.network_offline)
        }
    }
}
