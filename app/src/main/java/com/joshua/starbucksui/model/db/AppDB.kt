package com.joshua.starbucksui.model.db

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.joshua.starbucksui.R
import com.joshua.starbucksui.model.vo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.json.JSONException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.BufferedReader
import java.lang.reflect.Type
import java.util.ArrayList


@Database(entities = [RecordItem::class], version = 1, exportSchema = false)
@TypeConverters(
    RecordItemConverters::class,
    ViewTypeConverter::class
)
abstract class AppDB : RoomDatabase() {

    abstract fun appDao(): AppDao
    companion object {
        const val DB_NAME ="app.db"
        const val TABLE_RECORD = "Records"
        const val TABLE_VOUCHER = "Vouchers"
    }
}

class StartingDB(private val context: Context) : RoomDatabase.Callback(), KoinComponent {

    private val dao:AppDao by inject()

    override fun onCreate(db: SupportSQLiteDatabase) {
        fillWithStartingData(context, R.raw.record)
    }

    private fun fillWithStartingData(context: Context, resId:Int) {
        Log.i("AppDB", "dao=$dao")
        try {
           loadJsonArray(context, resId)?.let { datas->
               for (i in 0 until datas.length()) {
                   val item = datas.getJSONObject(i)
                   val id = item.getLong("id")

                   Log.i("AppDB", "item=$item")

                   CoroutineScope(Dispatchers.IO).launch {
                       dao.insertRecord(
                           RecordItem(
                               id= id,
                               type = ViewTypeConverter().toViewType(item.getInt("type")) ?: ViewType.TYPE_RECORD,
                               token= item.getString("token"),
                               tokenXnc= item.getString("tokenXnc"),
                               interaction=  item.getString("interaction"),
                               social= item.getString("social"),
                               revenue= item.getString("revenue"),
                               content = item.getString("content"),
                               time = item.getString("time"),
                               vendor = item.getString("vendor")
                           )
                       )
                   }


               }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJsonArray(context: Context, resId:Int): JSONArray? {
        val inputStream = context.resources.openRawResource(resId)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}

