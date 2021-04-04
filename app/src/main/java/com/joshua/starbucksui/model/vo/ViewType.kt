package com.joshua.starbucksui.model.vo

import androidx.room.TypeConverter

enum class ViewType(val value: Int){
    TYPE_RECORD(0),
    TYPE_VOUCHER(1),
    TYPE_AD(2)
}

class ViewTypeConverter {
    @TypeConverter
    fun fromViewType(type: ViewType?): Int {
        return when(type){
            ViewType.TYPE_RECORD ->0
            ViewType.TYPE_VOUCHER ->1
            ViewType.TYPE_AD ->2
            null -> -1
        }
    }

    @TypeConverter
    fun toViewType(value: Int): ViewType? {
        return when (value) {
            0 -> ViewType.TYPE_RECORD
            1 -> ViewType.TYPE_VOUCHER
            2 -> ViewType.TYPE_AD
            else -> null
        }
    }
}