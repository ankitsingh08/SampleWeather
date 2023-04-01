package com.example.weather.data.model

import com.example.weather.domain.model.CloudsDomainModel

data class Clouds(
    val all: Int
)

fun Clouds.toDomainModel(): CloudsDomainModel {
    return CloudsDomainModel(
        all = this.all
    )
}