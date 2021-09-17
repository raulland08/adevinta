package com.adevintaapp.domain.entity

data class BeerEntity(
    var id: Int,
    var name: String,
    var tagline: String? = null,
    var imageUrl: String? = null
)