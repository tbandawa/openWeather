package openweather.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import com.google.gson.GsonBuilder

class PreferenceHelper(val context: Context) : LiveData<PreferenceUnits>() {

    private var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun put(preferenceUnits: PreferenceUnits) {
        val jsonString = GsonBuilder().create().toJson(preferenceUnits)
        preferences.edit().putString("units", jsonString).apply()
        postValue(get())
    }

    fun get(): PreferenceUnits {
        val prefString = preferences.getString("units", "{\"distance\":\"m\",\"pressure\":\"hPa\",\"speed\":\"m/s\",\"temperature\":\"°C\",\"time\":\"12-hour\"}")
        return GsonBuilder().create().fromJson(prefString, PreferenceUnits::class.java)
    }

}
