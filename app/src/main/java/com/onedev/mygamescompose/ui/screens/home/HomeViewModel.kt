package com.onedev.mygamescompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.mygamescompose.core.model.Users
import com.onedev.mygamescompose.core.repository.GameRepository
import com.onedev.mygamescompose.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: GameRepository) : ViewModel() {

    private val _users: MutableStateFlow<UIState<Users>?> = MutableStateFlow(UIState.Loading)
    val users: StateFlow<UIState<Users>?>
        get() = _users

    fun users() {
        viewModelScope.launch {
            repository.users()
                .onEach {
                    _users.value = it
                }
                .launchIn(viewModelScope)
        }
    }
}