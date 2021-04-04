package com.joshua.starbucksui.model.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.joshua.starbucksui.model.db.AppDB.Companion.TABLE_RECORD

@Entity(tableName = TABLE_RECORD)
data class RecordItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Long =0,
    @ColumnInfo(name = "type")
    val type:ViewType =ViewType.TYPE_RECORD,
    @ColumnInfo(name = "token")
    val token: String = "",
    @ColumnInfo(name = "tokenXnc")
    val tokenXnc: String = "",
    @ColumnInfo(name = "interaction")
    val interaction: String = "",
    @ColumnInfo(name = "social")
    val social: String = "",
    @ColumnInfo(name = "revenue")
    val revenue: String = "",
    @ColumnInfo(name = "content")
    val content: String="",
    @ColumnInfo(name = "time")
    val time: String="",
    @ColumnInfo(name = "vendor")
    val vendor: String){
}

class RecordItemConverters {
    @TypeConverter
    fun itemToJson(item: RecordItem): String = Gson().toJson(item)

    @TypeConverter
    fun jsonToItem(value: String): RecordItem = Gson().fromJson(value, RecordItem::class.java)
}
