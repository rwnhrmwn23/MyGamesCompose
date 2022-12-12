package com.onedev.mygamescompose.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    val photo: String?,
    val genres: List<Genre>?,
    val id: Int?,
    val name: String?,
    val rating: Double?,
    val released: String?
) : Parcelable {
    @Parcelize
    data class Genre(
        val id: Int?,
        val name: String?
    ) : Parcelable
}