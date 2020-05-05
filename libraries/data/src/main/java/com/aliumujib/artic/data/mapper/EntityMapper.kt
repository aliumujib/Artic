/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aliumujib.artic.data.mapper

abstract class EntityMapper<E, D> {

    abstract fun mapFromEntity(entity: E): D

    abstract fun mapToEntity(domain: D): E

    fun mapFromEntityList(entities: List<E>): List<D> {
        val domainModels = mutableListOf<D>()
        entities.forEach {
            domainModels.add(mapFromEntity(it))
        }

        return domainModels
    }


    fun mapFromDomainList(domainModels: List<D>): List<E> {
        val entities = mutableListOf<E>()
        domainModels.forEach {
            entities.add(mapToEntity(it))
        }

        return entities
    }
}
