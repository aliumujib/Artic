package com.aliumujib.artic.remote.mapper

import java.text.SimpleDateFormat
import java.util.*

interface RemoteModelMapper<in M, out E> {

    fun mapFromModel(model: M): E


    fun mapModelList(models: List<M>?): List<E> {
        val list = mutableListOf<E>()
        models?.forEach {
            list.add(mapFromModel(it))
        }

        return list
    }

    fun safeString(string: String?): String {
        return string ?: "N/A"
    }

    fun safeParse(format: SimpleDateFormat, from: String): Date {
        return format.parse(from)
    }

}