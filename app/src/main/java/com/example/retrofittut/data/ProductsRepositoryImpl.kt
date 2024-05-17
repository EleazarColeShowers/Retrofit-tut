package com.example.retrofittut.data

import com.example.retrofittut.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductsRepositoryImpl(
    private val api: Api
): ProductsRepository {
    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromApi= try {
                api.getProductsList()
            }catch (e: IOException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }catch (e: Exception){
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }

            emit(Result.Success(productsFromApi.products))
        }

    }
}