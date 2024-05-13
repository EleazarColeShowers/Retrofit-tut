package com.example.retrofittut.data

import com.example.retrofittut.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImpl(
    private val api: Api
): ProductRepository {
    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromApi= try {

            }catch (e: IOException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
            }catch (e: HttpException){

            }
        }
    }
}