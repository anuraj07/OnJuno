package com.assignment.onjuno.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.onjuno.data.emptyState
import com.assignment.onjuno.data.valueState
import com.assignment.onjuno.repository.ValueRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ValueRepository) : ViewModel() {

    val emptyStateLiveData: LiveData<emptyState> get() = repository.emptyState
    val valueStateLiveData: LiveData<valueState> get() = repository.valueState

    init {
        viewModelScope.launch {
            repository.getEmptyState()
            repository.getValueState()
        }
    }

}