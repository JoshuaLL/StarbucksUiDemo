package com.joshua.starbucksui.ui.wallet.walletlist

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.joshua.starbucksui.model.db.AppDbRepository
import com.joshua.starbucksui.model.vo.ViewIntent
import com.joshua.starbucksui.model.vo.ViewState
import com.joshua.starbucksui.model.vo.ViewState.*
import com.joshua.starbucksui.model.vo.ViewType
import com.joshua.starbucksui.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class WalletListViewModel : BaseViewModel() {

    companion object{
        const val PAGE_SIZE =5
    }

    private val dbRepo: AppDbRepository by inject()

    val fetchIntent = Channel<ViewIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<ViewState>(Idle)
    val state: StateFlow<ViewState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            fetchIntent.consumeAsFlow().collect {
                when (it) {
                    is ViewIntent.FetchRecordData -> getRecordList(ViewType.TYPE_RECORD)
                    is ViewIntent.FetchVoucherData -> getRecordList(ViewType.TYPE_VOUCHER)
                }
            }
        }
    }

    private suspend fun getRecordList(type:ViewType) {
        _state.value = Loading
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dbRepo.getAllRecords(type) }
        ).flow.cachedIn(viewModelScope).collectLatest{
            _state.value = RecordItems(it)
        }
    }

}