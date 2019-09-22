package com.aliumujib.artic.remote.mapper

import io.reactivex.exceptions.Exceptions
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
        val date = try {
            format.parse(from)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Exceptions.propagate(ex)
            Date()
        }
        return date
    }

}