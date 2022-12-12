package com.onedev.mygamescompose.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    val photo: String?,
    val id: Int?,
    val name: String?,
    val rating: Double?,
    val released: String?
) : Parcelable