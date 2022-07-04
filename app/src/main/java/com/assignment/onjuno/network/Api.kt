package com.assignment.onjuno.network

import com.assignment.onjuno.data.emptyState
import com.assignment.onjuno.data.valueState
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("empty-home")
    suspend fun getEmptyState() : Response<emptyState>

    @GET("home")
    suspend fun getValueState() : Response<valueState>

}