package com.onedev.mygamescompose.utils

import com.onedev.mygamescompose.core.data.source.remote.response.GamesResponse
import com.onedev.mygamescompose.core.domain.model.Games

object Mapper {
    fun GamesResponse?.mapToGames(): List<Games> {
        val datas = ArrayList<Games>()
        val genreGames = ArrayList<Games.Genre>()

        this?.results?.map {
            it.genres?.map { g ->
                genreGames.add(
                    Games.Genre(g.id, g.name)
                )
            }
            datas.add(
                Games(
                    it.background_image,
                    genreGames,
                    it.id,
                    it.name,
                    it.rating,
                    it.released,
                )
            )

        }
        return datas
    }
}