package com.example.cryptocurrency.presentation.coin_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class CoinListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private  val getCoinsUseCase: GetCoinsUseCase
):BaseViewModel(contextProvider){

    private val _state = MutableLiveData<CoinListState>(CoinListState())
    val state: MutableLiveData<CoinListState> = _state

     fun getCoins() {
        getCoinsUseCase().onEach {result ->
            when(result){
                is Resource.Success ->{
                    _state.value = CoinListState(coins = result.data?: emptyList())
                }
                is Resource.Loading ->{
                    _state.value = CoinListState(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = CoinListState(error = result.message?:"An Unexpected Error Occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")

}