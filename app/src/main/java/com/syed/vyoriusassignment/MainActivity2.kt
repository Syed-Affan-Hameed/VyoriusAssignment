package com.syed.vyoriusassignment

import android.Manifest
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.GoogleMap
import com.syed.vyoriusassignment.MainActivity2
import com.syed.vyoriusassignment.R
import android.os.Bundle
import android.os.Build
import androidx.core.app.ActivityCompat
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.RadioGroup
import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity2 : FragmentActivity(), View.OnClickListener, GoogleMap.OnMapClickListener, OnMapReadyCallback {
    private var gMap: GoogleMap? = null
    private var locate: Button? = null
    private var add: Button? = null
    private var clear: Button? = null
    private var config: Button? = null
    private var upload: Button? = null
    private var start: Button? = null
    private var stop: Button? = null
    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * @Description : RETURN BTN RESPONSE FUNCTION
     */
    fun onReturn(view: View?) {
        Log.d(TAG, "onReturn")
        finish()
    }

    private fun initUI() {
        locate = findViewById<View>(R.id.locate) as Button
        add = findViewById<View>(R.id.add) as Button
        clear = findViewById<View>(R.id.clear) as Button
        config = findViewById<View>(R.id.config) as Button
        upload = findViewById<View>(R.id.upload) as Button
        start = findViewById<View>(R.id.start) as Button
        stop = findViewById<View>(R.id.stop) as Button
        locate!!.setOnClickListener(this)
        add!!.setOnClickListener(this)
        clear!!.setOnClickListener(this)
        config!!.setOnClickListener(this)
        upload!!.setOnClickListener(this)
        start!!.setOnClickListener(this)
        stop!!.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // When the compile and target version is higher than 22, please request the
        // following permissions at runtime to ensure the
        // SDK work well.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.VIBRATE,
                    Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.WAKE_LOCK, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.READ_PHONE_STATE), 1)
        }
        setContentView(R.layout.activity_main)
        initUI()
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    private fun showSettingDialog() {
        val wayPointSettings = layoutInflater.inflate(R.layout.dialog_waypointsetting, null) as LinearLayout
        val wpAltitude_TV = wayPointSettings.findViewById<View>(R.id.altitude) as TextView
        val speed_RG = wayPointSettings.findViewById<View>(R.id.speed) as RadioGroup
        val actionAfterFinished_RG = wayPointSettings.findViewById<View>(R.id.actionAfterFinished) as RadioGroup
        val heading_RG = wayPointSettings.findViewById<View>(R.id.heading) as RadioGroup
        speed_RG.setOnCheckedChangeListener { group, checkedId -> // TODO Auto-generated method stub
            Log.d(TAG, "Select Speed finish")
        }
        actionAfterFinished_RG.setOnCheckedChangeListener { group, checkedId -> // TODO Auto-generated method stub
            Log.d(TAG, "Select action action")
        }
        heading_RG.setOnCheckedChangeListener { group, checkedId -> // TODO Auto-generated method stub
            Log.d(TAG, "Select heading finish")
        }
        AlertDialog.Builder(this)
                .setTitle("")
                .setView(wayPointSettings)
                .setPositiveButton("Finish") { dialog, id -> }
                .setNegativeButton("Cancel") { dialog, id -> dialog.cancel() }
                .create()
                .show()
    }

    override fun onClick(v: View) {
        // TODO Auto-generated method stub
        when (v.id) {
            R.id.config -> {
                showSettingDialog()
            }
            else -> {
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // TODO Auto-generated method stub
        // Initializing Amap object
        if (gMap == null) {
            gMap = googleMap
            setUpMap()
        }
        val shenzhen = LatLng(22.5362, 113.9454)
        gMap!!.addMarker(MarkerOptions().position(shenzhen).title("Marker in Shenzhen"))
        gMap!!.moveCamera(CameraUpdateFactory.newLatLng(shenzhen))
    }

    private fun setUpMap() {
        gMap!!.setOnMapClickListener(this) // add the listener for click for amap object
    }

    override fun onMapClick(point: LatLng) {}

    companion object {
        protected const val TAG = "MainActivity"
    }
}