package com.onedev.mygamescompose.core.domain.repository

import com.onedev.mygamescompose.core.data.source.remote.network.StateEvent
import com.onedev.mygamescompose.core.domain.model.Users
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun users(): Flow<StateEvent<List<Users>>>
}