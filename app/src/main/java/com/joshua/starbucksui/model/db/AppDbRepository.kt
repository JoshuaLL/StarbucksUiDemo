package com.joshua.starbucksui.model.db

import com.joshua.starbucksui.model.vo.RecordItem
import com.joshua.starbucksui.model.vo.ViewType


class AppDbRepository (private val dao: AppDao) {
    fun getAllRecords(type: ViewType) = dao.recordPagingSource(type)
    fun insertRecord(item: RecordItem) = dao.insertRecord(item)
}
