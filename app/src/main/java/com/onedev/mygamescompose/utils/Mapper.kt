package com.onedev.mygamescompose.utils

import com.onedev.mygamescompose.core.data.source.remote.response.GameDetailsResponse
import com.onedev.mygamescompose.core.data.source.remote.response.GamesResponse
import com.onedev.mygamescompose.core.domain.model.GameDetails
import com.onedev.mygamescompose.core.domain.model.Games

object Mapper {
    fun GamesResponse?.mapToGames(): List<Games> {
        val datas = ArrayList<Games>()

        this?.results?.map {
            datas.add(
                Games(
                    it.background_image,
                    it.id,
                    it.name,
                    it.rating,
                    it.released,
                )
            )

        }
        return datas
    }

    fun GameDetailsResponse?.mapToDetailGames(): GameDetails {
        return GameDetails(
            this?.background_image,
            this?.description,
            this?.id,
            this?.name,
            this?.released,
            this?.website,
        )
    }
}