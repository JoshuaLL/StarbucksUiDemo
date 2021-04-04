package com.joshua.starbucksui.model.db

import androidx.paging.PagingSource
import androidx.room.*
import com.joshua.starbucksui.model.vo.RecordItem
import com.joshua.starbucksui.model.vo.ViewType

/**
 * Room data access object for storing and querying teas by various criteria.
 * @see Dao
 */
@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(item: RecordItem) : Long

    @Transaction
    @Query("SELECT * FROM Records WHERE type =:type")
    fun recordPagingSource(type: ViewType): PagingSource<Int, RecordItem>

}
