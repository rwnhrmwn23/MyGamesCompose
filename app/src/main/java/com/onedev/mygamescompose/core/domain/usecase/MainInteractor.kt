package com.onedev.mygamescompose.core.domain.usecase

import com.onedev.mygamescompose.core.domain.repository.MainRepository
import javax.inject.Inject

class MainInteractor @Inject constructor (private val mainRepository: MainRepository) : MainUseCase {
    override fun games(pageSize: Int) = mainRepository.games(pageSize)
    override fun detailsGame(id: Int) = mainRepository.detailsGame(id)
}