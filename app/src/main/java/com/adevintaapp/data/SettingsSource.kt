package com.adevintaapp.data

import com.adevintaapp.data.settings.SettingsRaw
import com.adevintaapp.domain.entity.SettingsEntity

class SettingsSource {

    fun mapSettings(settingsRaw: SettingsRaw): SettingsEntity {
        return SettingsEntity(
            nameSize = settingsRaw.nameSize,
            nameColor = settingsRaw.nameColor,
            taglineSize = settingsRaw.taglineSize,
            taglineColor = settingsRaw.taglineColor,
            taglineVisible = settingsRaw.taglineVisible,
            imageHeight = settingsRaw.imageHeight,
            imageWidth = settingsRaw.imageWidth,
            imageRoundRadius = settingsRaw.imageRoundRadius
        )
    }
}