package com.poojasinghandroid.todoapp.data.local

import androidx.room.TypeConverter
import com.poojasinghandroid.todoapp.domain.model.Priority

class Converters {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(value: String): Priority {
        return Priority.valueOf(value)
    }
}