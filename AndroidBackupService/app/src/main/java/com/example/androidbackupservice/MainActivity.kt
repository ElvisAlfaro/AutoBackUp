package com.example.androidbackupservice

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

const val TAG = "BACKUP_AUTO"
//const val PREFERENCE_NAME = "Preference_test"

class MainActivity : AppCompatActivity() {
    var preferences: SharedPreferences? = null
    val DEVICE_ID = "DEVICE_ID"
    lateinit var etValue : EditText
    lateinit var  btnSaveValue : Button
    lateinit var btnConsultar : Button
    lateinit var tvResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etValue = findViewById(R.id.etValue)
        btnSaveValue = findViewById(R.id.btnSaveBackup)
        btnConsultar = findViewById(R.id.btnConsultar)
        tvResult = findViewById(R.id.tvResult)

        btnSaveValue.setOnClickListener {
            if (preferences == null) {
                preferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            }
            val edit = preferences?.edit()
            if (!preferences?.contains(DEVICE_ID)!!) {
                Log.d(TAG, "NO EXISTE DEVICE ID - VALUE")
                edit?.putString(DEVICE_ID, etValue.text.toString())
                edit?.commit()
                etValue.setText("")
            }
            else {
                Log.d(TAG, "YA EXISTE DEVICE ID - VALUE")
            }
        }

        btnConsultar.setOnClickListener {
            if (preferences == null) {
                preferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            }
            val device_id = preferences?.getString(DEVICE_ID, "")
            Log.d(TAG, "device_id - $device_id")
            tvResult.text = device_id
        }

    }
}