package com.assignment.onjuno.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.onjuno.data.emptyState
import com.assignment.onjuno.data.valueState
import com.assignment.onjuno.network.Api
import javax.inject.Inject

class ValueRepository @Inject constructor(private val api: Api) {

    private val _emptyState = MutableLiveData<emptyState>()
    val emptyState: LiveData<emptyState> get() = _emptyState

    private val _valueState = MutableLiveData<valueState>()
    val valueState: LiveData<valueState> get() = _valueState

    suspend fun getEmptyState() {
        val result = api.getEmptyState()
        if (result.isSuccessful && result.body() != null) {
            _emptyState.postValue(result.body())
        }
    }

    suspend fun getValueState() {
        val result = api.getValueState()
        if (result.isSuccessful && result.body() != null) {
            _valueState.postValue(result.body())
        }
    }
}