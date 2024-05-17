package com.example.retrofittut.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittut.data.ProductsRepository
import com.example.retrofittut.data.Result
import com.example.retrofittut.data.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productRepository: ProductsRepository
): ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()
    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel= _showErrorToastChannel.receiveAsFlow()
    init{
        viewModelScope.launch {
            productRepository.getProductList().collectLatest { result->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let {products ->
                            _products.update { products  }
                        }
                    }
                }
            }
        }
    }
}