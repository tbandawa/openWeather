package openweather.data.local

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import openweather.data.UnitsPreferences
import openweather.domain.datastore.UnitsPreferencesDataStore
import openweather.domain.models.PreferenceUnits
import java.io.InputStream
import java.io.OutputStream

class UnitsPreferencesDataStoreImpl(val context: Context): UnitsPreferencesDataStore {

    private val Context.unitsPreferencesDataStore: DataStore<UnitsPreferences> by dataStore(
        fileName = "units_prefs.pb",
        serializer = UnitsPreferencesSerializer
    )

    override var preferencesUnits: Flow<PreferenceUnits> = context.unitsPreferencesDataStore.data
        .map { unitsPreferences ->
            PreferenceUnits(
                unitsPreferences.temperature,
                unitsPreferences.speed,
                unitsPreferences.pressure,
                unitsPreferences.time
            )
        }

    override suspend fun savePreferencesUnits(preferenceUnits: PreferenceUnits) {
        context.unitsPreferencesDataStore.updateData { unitsPreferences ->
            unitsPreferences.toBuilder()
                .setTemperature(preferenceUnits.temperature)
                .setSpeed(preferenceUnits.speed)
                .setPressure(preferenceUnits.pressure)
                .setTime(preferenceUnits.time)
                .build()
        }
    }

    object UnitsPreferencesSerializer : Serializer<UnitsPreferences> {

        override val defaultValue: UnitsPreferences = UnitsPreferences.getDefaultInstance()

        override suspend fun readFrom(input: InputStream): UnitsPreferences {
            try {
                return UnitsPreferences.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override suspend fun writeTo(t: UnitsPreferences, output: OutputStream) = t.writeTo(output)

    }
}