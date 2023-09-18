package com.gema.moviewapps.data

import com.gema.moviewapps.data.remote.RemoteDataSource
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getDataMoview(token: String) = remoteDataSource.dataMovie(token)
}