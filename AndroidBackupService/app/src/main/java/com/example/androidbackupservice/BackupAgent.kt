package com.example.androidbackupservice

import android.app.backup.BackupAgentHelper
import android.app.backup.BackupDataInput
import android.app.backup.BackupDataOutput
import android.app.backup.SharedPreferencesBackupHelper
import android.os.ParcelFileDescriptor
import java.io.DataInputStream
import java.io.FileInputStream
import java.io.IOException

const val PREFERENCE_NAME = "preference_test"

class BackupAgent : BackupAgentHelper() {
    val PREFERENCE_HELPER = "PREFERENCE_HELPER"
    lateinit var preferenceHelper : SharedPreferencesBackupHelper
/*
    constructor(namePref : String) {
        preferenceHelper = SharedPreferencesBackupHelper(this, namePref)
        addHelper(PREFERENCE_HELPER, preferenceHelper)
    }
*/
    override fun onCreate() {
        super.onCreate()
        preferenceHelper = SharedPreferencesBackupHelper(this, PREFERENCE_NAME)
        addHelper(PREFERENCE_HELPER, preferenceHelper)
    }

    override fun onBackup(oldState: ParcelFileDescriptor?, data: BackupDataOutput?, newState: ParcelFileDescriptor?) {
        super.onBackup(oldState, data, newState)
        val instream = FileInputStream(oldState?.fileDescriptor)
        val dataInputStream = DataInputStream(instream)

        try {
            val stateModified = dataInputStream.readLong()
            //val fileModified : Long = data.las
        }
        catch (ex : IOException) {}
    }

    override fun onRestore(data: BackupDataInput?, appVersionCode: Int, newState: ParcelFileDescriptor?) {
        super.onRestore(data, appVersionCode, newState)
        //data.
    }
}