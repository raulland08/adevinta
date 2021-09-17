package com.adevintaapp.data.beer

import kotlinx.serialization.Serializable

@Serializable
data class BeerRaw (
    var id: Int,
    var name: String,
    var tagline: String? = null,
    var first_brew: String? = null,
    var description: String? = null,
    var image_url: String? = null,
    var abv: String? = null,
    var ibu: String? = null,
    var target_fg: String? = null,
    var target_og: String? = null,
    var ebc: String? = null,
    var srm: String? = null,
    var ph: String? = null,
    var attenuation_level: String? = null,
    var volume: ValueRaw? = null,
    var boil_volume: ValueRaw? = null,
    var method: MethodRaw? = null,
    var ingredients: IngredientsRaw? = null,
    var foodPairing: List<String>? = null,
    var brewers_tips: String? = null,
    var contributed_by: String? = null
)

@Serializable
data class ValueRaw (
    var value: Float,
    var unit: String
)

@Serializable
data class MethodRaw (
    var mash_temp: List<MashTempRaw>,
    var fermentation: FermentationRaw,
    var twist: String
)

@Serializable
data class MashTempRaw (
    var temp: ValueRaw,
    var duration: Int
)

@Serializable
data class FermentationRaw (
    var temp: ValueRaw
)

@Serializable
data class IngredientsRaw (
    var malt: List<MaltRaw>,
    var hops: List<HopsRaw>,
    var yeast: String
)

@Serializable
data class MaltRaw (
    var name: String,
    var amount: ValueRaw
)

@Serializable
data class HopsRaw (
    var name: String,
    var amount: ValueRaw,
    var add: String,
    var attribute: String
)