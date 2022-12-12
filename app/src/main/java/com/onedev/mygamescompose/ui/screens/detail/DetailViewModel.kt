package com.onedev.mygamescompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.GameDetails
import com.onedev.mygamescompose.core.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (private val mainUseCase: MainUseCase) : ViewModel() {

    private val _games: MutableStateFlow<StateEvent<GameDetails>> = MutableStateFlow(StateEvent.Loading())
    val games: StateFlow<StateEvent<GameDetails>>
        get() = _games

    fun detailsGame(id: Int) {
        viewModelScope.launch {
            mainUseCase.detailsGame(id)
                .catch {
                    _games.value = StateEvent.Error(it.message.toString())
                }
                .collect { data ->
                    _games.value = data
                }
        }
    }
}