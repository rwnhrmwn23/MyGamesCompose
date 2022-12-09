package com.onedev.mygamescompose.core.model

data class Users(
    val `data`: List<Data>?,
    val page: Int?,
    val per_page: Int?,
    val total: Int?,
    val total_pages: Int?
) {
    data class Data(
        val avatar: String?,
        val email: String?,
        val first_name: String?,
        val id: Int?,
        val last_name: String?
    )
}