package me.tbandawa.android.openweather

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import openweather.data.local.PreferenceHelper
import openweather.data.local.PreferenceUnits
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper
) : ViewModel() {

    /*var prefUnits: MutableState<PreferenceUnits> = mutableStateOf(preferenceHelper.get())

    init {
        viewModelScope.launch {
            preferenceHelper.prefUnits.collect { results ->
                prefUnits.value = results
            }
        }
    }

    fun getUnits(): PreferenceUnits {
        return preferenceHelper.get()
    }*/

    fun setUnits(preferenceUnits: PreferenceUnits) {
        preferenceHelper.put(preferenceUnits)
    }

}