package openweather.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import com.google.gson.GsonBuilder

class PreferenceHelper(val context: Context) : LiveData<PreferenceUnits>() {

    private lateinit var preferences: SharedPreferences

    override fun onActive() {
        super.onActive()
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        postValue(get())
    }

    fun put(preferenceUnits: PreferenceUnits) {
        val jsonString = GsonBuilder().create().toJson(preferenceUnits)
        preferences.edit().putString("units", jsonString).apply()
        postValue(preferenceUnits)
    }

    private fun get(): PreferenceUnits {
        val prefString = preferences.getString("units", "{\"distance\":\"km\",\"pressure\":\"hPa\",\"speed\":\"m/s\",\"temperature\":\"°C\",\"time\":\"12-hour\"}")
        return GsonBuilder().create().fromJson(prefString, PreferenceUnits::class.java)
    }

}