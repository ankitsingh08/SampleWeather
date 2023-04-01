package com.example.weather.data.model

import com.example.weather.domain.model.WindDomainModel

data class Wind(
    val deg: Double,
    val speed: Double
)

fun Wind.toDomainModel(): WindDomainModel {
    return WindDomainModel(
        deg = this.deg,
        speed = this.speed
    )
}