package com.aliumujib.artic.cache.models.mappers

import java.text.SimpleDateFormat
import java.util.*

interface CacheModelMapper<M, E> {

    fun mapToEntity(model: M): E

    fun mapToModel(entity: E): M

    fun mapToEntityList(models: List<M>?): List<E> {
        val list = mutableListOf<E>()
        models?.forEach {
            list.add(mapToEntity(it))
        }

        return list
    }

    fun mapToModelList(models: List<E>?): List<M> {
        val list = mutableListOf<M>()
        models?.forEach {
            list.add(mapToModel(it))
        }

        return list
    }

    fun safeList(models: List<Any>?): List<Any> {
        return models ?: emptyList()
    }

    fun safeString(string: String?): String {
        return string ?: "N/A"
    }

    fun safeBoolean(boolean: Boolean?): Boolean {
        return boolean ?: false
    }

    fun safeParse(format: SimpleDateFormat, from: String): Date {
        return format.parse(from)
    }

}