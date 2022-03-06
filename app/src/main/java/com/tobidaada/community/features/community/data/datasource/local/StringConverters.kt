package com.tobidaada.community.features.community.data.datasource.local

import androidx.room.TypeConverter

private const val SEPARATOR = ","
class StringConverters {

    @TypeConverter
    fun fromStringToList(input: String): List<String> = input.split(SEPARATOR)

    @TypeConverter
    fun fromListToString(list: List<String>): String = list.joinToString(separator = SEPARATOR)
}