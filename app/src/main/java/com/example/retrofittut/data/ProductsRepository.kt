package com.example.retrofittut.data

import com.example.retrofittut.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductList(): Flow<Result<List<Product>>>
}