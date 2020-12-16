package com.example.ukagirdictionary.utils

import android.app.Activity
import android.util.Log
import com.example.ukagirdictionary.data.Data
import com.google.gson.Gson
import java.lang.Exception

class JsonUtil {

    fun parseJson(jsonFile: String, activity: Activity): Data {
        return Gson().fromJson(readLocalJson(jsonFile, activity), Data::class.java)
    }

    private fun readLocalJson(jsonFile: String, activity: Activity): String {
        var json = ""
        try {
            val inputStream = activity.assets.open(jsonFile)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: Exception) {
            Log.e("read local", e.toString())
        }
        return json
    }
}