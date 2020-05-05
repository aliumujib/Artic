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
package com.aliumujib.artic.views.mappers

abstract class UIModelMapper<D, U> {

    abstract fun mapToUI(entity: D): U

    abstract fun mapFromUI(domain: U): D

    fun mapToUIList(entities: List<D>): List<U> {
        val models = mutableListOf<U>()
        entities.forEach {
            models.add(mapToUI(it))
        }

        return models
    }


    fun mapFromUIList(uiModels: List<U>): List<D> {
        val domains = mutableListOf<D>()
        uiModels.forEach {
            domains.add(mapFromUI(it))
        }

        return domains
    }
}
