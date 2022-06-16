package com.example.cryptocurrency.presentation.coin_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptocurrency.presentation.base.BaseViewModel
import com.example.cryptocurrency.presentation.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    contextProvider:CoroutineContextProvider,
    private  val getCoinUseCase: GetCoinUseCase,
//    private val savedStateHandle: SavedStateHandle
):BaseViewModel(contextProvider){

    private val _state = MutableLiveData<CoinDetailState>(CoinDetailState())
    val state: MutableLiveData<CoinDetailState> = _state

//    init {
//        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
//            coinId-> getCoin(coinId)
//        }
//    }

     fun getCoin(coinId:String) {
        getCoinUseCase(coinId).onEach {result ->
            when(result){
                is Resource.Success ->{
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Loading ->{
                    _state.value = CoinDetailState(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = CoinDetailState(error = result.message?:"An Unexpected Error Occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")
}