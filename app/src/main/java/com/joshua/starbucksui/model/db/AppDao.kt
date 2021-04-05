package com.joshua.starbucksui.model.db

import androidx.paging.PagingSource
import androidx.room.*
import com.joshua.starbucksui.model.vo.RecordItem
import com.joshua.starbucksui.model.vo.ViewType

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(item: RecordItem) : Long

    @Transaction
    @Query("SELECT * FROM Records WHERE type =:type or type=:type2")
    fun recordPagingSource(type: ViewType, type2: ViewType): PagingSource<Int, RecordItem>

}
