package com.onedev.mygamescompose.utils

import com.onedev.mygamescompose.core.data.source.remote.response.UsersResponse
import com.onedev.mygamescompose.core.domain.model.Users

object Mapper {
    fun UsersResponse?.mapToUsers(): List<Users> {
        val datas = ArrayList<Users>()
        this?.data?.map {
            datas.add(
                Users(
                    it.avatar,
                    it.email,
                    it.first_name,
                    it.id,
                    it.last_name
                )
            )

        }
        return datas
    }
}