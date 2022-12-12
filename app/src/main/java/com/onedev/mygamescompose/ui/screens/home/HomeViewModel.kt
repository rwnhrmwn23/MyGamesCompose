package com.onedev.mygamescompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.Games
import com.onedev.mygamescompose.core.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val mainUseCase: MainUseCase) : ViewModel() {

    private val _games: MutableStateFlow<StateEvent<List<Games>>> = MutableStateFlow(StateEvent.Loading())
    val games: StateFlow<StateEvent<List<Games>>>
        get() = _games

    fun games(pageSize: Int) {
        viewModelScope.launch {
            mainUseCase.games(pageSize)
                .catch {
                    _games.value = StateEvent.Error(it.message.toString())
                }
                .collect { data ->
                    _games.value = data
                }
        }
    }
}