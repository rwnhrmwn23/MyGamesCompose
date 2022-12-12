package com.onedev.mygamescompose.core.data.source.remote.response

data class GamesResponse(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<Result>?
) {
    data class Result(
        val background_image: String?,
        val genres: List<Genre>?,
        val id: Int?,
        val name: String?,
        val rating: Double?,
        val released: String?
    ) {
        data class Genre(
            val id: Int?,
            val name: String?
        )
    }
}