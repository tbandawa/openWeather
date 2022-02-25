package openweather.domain.datastore

import kotlinx.coroutines.flow.Flow
import openweather.domain.models.PreferenceUnits

interface UnitsPreferencesDataStore {

    var preferencesUnits: Flow<PreferenceUnits>

    suspend fun savePreferencesUnits(preferenceUnits: PreferenceUnits)

}