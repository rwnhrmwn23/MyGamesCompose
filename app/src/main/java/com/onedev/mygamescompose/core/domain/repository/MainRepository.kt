package com.onedev.mygamescompose.core.domain.repository

import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.GameDetails
import com.onedev.mygamescompose.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun games(pageSize: Int): Flow<StateEvent<List<Games>>>
    fun detailsGame(id: Int): Flow<StateEvent<GameDetails>>
}