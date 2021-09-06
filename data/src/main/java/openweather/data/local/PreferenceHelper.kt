package openweather.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class PreferenceHelper(private val context: Context) {

    private val _tempUnit = MutableLiveData<String>()
    val tempUnit: LiveData<String> = _tempUnit

    private val _speedUnit = MutableLiveData<String>()
    val speedUnit: LiveData<String> = _speedUnit

    private val _pressureUnit = MutableLiveData<String>()
    val pressureUnit: LiveData<String> = _pressureUnit

    private val _distanceUnit = MutableLiveData<String>()
    val distanceUnit: LiveData<String> = _distanceUnit

    private val _timeUnit = MutableLiveData<String>()
    val timeUnit: LiveData<String> = _timeUnit

    init {
        emitPreferences()
    }

    fun defaultPrefs(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * Puts a key value pair in shared preferenceManager if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: String?, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
        emitPreferences()
    }

    /**
     * Finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    operator inline fun <reified T : Any> SharedPreferences.get(key: String?, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * Updates preferences to observers
     */
    private fun emitPreferences() {
        _tempUnit.value = defaultPrefs().getString(Units.TEMPERATURE.key, "°C")
        _speedUnit.value = defaultPrefs().getString(Units.SPEED.key, "m/s")
        _pressureUnit.value = defaultPrefs().getString(Units.PRESSURE.key, "hPa")
        _distanceUnit.value = defaultPrefs().getString(Units.DISTANCE.key, "km")
        _timeUnit.value = defaultPrefs().getString(Units.TIME.key, "24-hour")
    }

}