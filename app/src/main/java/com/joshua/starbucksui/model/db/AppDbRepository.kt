package com.joshua.starbucksui.model.db

import com.joshua.starbucksui.model.vo.RecordItem
import com.joshua.starbucksui.model.vo.ViewType


class AppDbRepository (private val dao: AppDao) {
    fun getAllRecords(type: ViewType, type2: ViewType) = dao.recordPagingSource(type, type2)
    fun insertRecord(item: RecordItem) = dao.insertRecord(item)
}
