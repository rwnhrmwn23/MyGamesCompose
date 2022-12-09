package com.onedev.mygamescompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.Users
import com.onedev.mygamescompose.core.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val mainUseCase: MainUseCase) : ViewModel() {

    private val _users: MutableStateFlow<StateEvent<List<Users>>> = MutableStateFlow(StateEvent.Loading())
    val users: StateFlow<StateEvent<List<Users>>>
        get() = _users

    fun users() {
        viewModelScope.launch {
            mainUseCase.users()
                .catch {
                    _users.value = StateEvent.Error(it.message.toString())
                }
                .collect { data ->
                    _users.value = data
                }
        }
    }
}