package com.example.retrofittut.data

import com.example.retrofittut.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductList(): Flow<Result<List<Product>>>
}