package openweather.data.local

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import openweather.data.UnitsPreferences
import java.io.InputStream
import java.io.OutputStream

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