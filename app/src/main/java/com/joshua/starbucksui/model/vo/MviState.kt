package com.joshua.starbucksui.model.vo

import androidx.paging.PagingData

sealed class ViewIntent {
    object FetchRecordData : ViewIntent()
    object FetchVoucherData : ViewIntent()
}

sealed class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    data class RecordItems(val records: PagingData<RecordItem>) : ViewState()
    data class Error(val error: String?) : ViewState()
}
