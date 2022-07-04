package com.assignment.onjuno.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.onjuno.repository.ValueRepository
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val repository: ValueRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}