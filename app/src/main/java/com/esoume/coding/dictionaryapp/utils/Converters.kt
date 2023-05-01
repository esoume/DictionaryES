package com.esoume.coding.dictionaryapp.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.esoume.coding.dictionaryapp.domain.model.License
import com.esoume.coding.dictionaryapp.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object: TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String?{
        return jsonParser.toJson(
            meanings,
            object: TypeToken<ArrayList<Meaning>>(){}.type
        )?: "[]"
    }

    @TypeConverter
    fun fromLicenseJson(json: String): License{
        return jsonParser.fromJson<License>(
            json,
            object: TypeToken<License>(){}.type
        ) ?: License("","")
    }

    @TypeConverter
    fun toLicenseJson(license: License): String?{
        return jsonParser.toJson(
            license,
            object: TypeToken<License>(){}.type
        )?: "[]"
    }

    @TypeConverter
    fun fromSourceUrlsJson(json: String): List<String>{
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object: TypeToken<ArrayList<String>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toSourceUrlsJson(sourceUrls: List<String>): String?{
        return jsonParser.toJson(
            sourceUrls,
            object: TypeToken<ArrayList<String>>(){}.type
        )?: "[]"
    }
}