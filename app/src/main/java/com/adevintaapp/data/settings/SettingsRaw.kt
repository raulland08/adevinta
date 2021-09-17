package com.adevintaapp.data.settings

data class SettingsRaw(
    var nameSize: Float = 0f,
    var nameColor: String? = null,
    var taglineSize: Float = 0f,
    var taglineColor: String? = null,
    var taglineVisible: Boolean,
    var imageHeight: Int = 0,
    var imageWidth: Int = 0,
    var imageRoundRadius: Int = 0
)