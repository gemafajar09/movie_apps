package com.gema.moviewapps.data.remote

import android.util.Log
import com.gema.moviewapps.data.Resouce
import com.gema.moviewapps.data.remote.network.ApiService
import com.gema.moviewapps.data.remote.response.MoviewResponse
import com.gema.moviewapps.util.Constan.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun dataMovie(token: String) = flow<Resouce<MoviewResponse>> {
        emit(Resouce.Loading())
        val response = apiService.dataMovie(token)
        response.let {
            if(it.results!!.isNotEmpty()) emit(Resouce.Success(it))
            else emit(Resouce.Error("Data User Tidak Ditemukan"))
        }
    }.catch {
        Log.d(TAG, it.message.toString())
    }.flowOn(Dispatchers.IO)

}