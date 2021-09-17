package com.adevintaapp.data

import android.content.SharedPreferences
import com.adevintaapp.data.beer.BeerServiceApi
import com.adevintaapp.data.settings.SettingsRaw
import com.adevintaapp.domain.entity.BeerEntity
import com.adevintaapp.domain.entity.SettingsEntity
import com.adevintaapp.framework.local.PreferenceHelper.set
import com.adevintaapp.framework.local.PrefsKeys.Companion.IMAGE_HEIGHT
import com.adevintaapp.framework.local.PrefsKeys.Companion.IMAGE_ROUND_RADIUS
import com.adevintaapp.framework.local.PrefsKeys.Companion.IMAGE_WIDTH
import com.adevintaapp.framework.local.PrefsKeys.Companion.NAME_COLOR
import com.adevintaapp.framework.local.PrefsKeys.Companion.NAME_SIZE
import com.adevintaapp.framework.local.PrefsKeys.Companion.TAGLINE_COLOR
import com.adevintaapp.framework.local.PrefsKeys.Companion.TAGLINE_SIZE
import com.adevintaapp.framework.local.PrefsKeys.Companion.TAGLINE_VISIBLE

class BeerRepository(
    private val beerApi: BeerServiceApi,
    private val beerSource: BeerSource,
    private val settingsSource: SettingsSource,
    private val sharedPrefs: SharedPreferences
) {
    suspend fun getRandomBeer(): BeerEntity {
        try {
            val response = beerApi.getRandomBeer()
            return beerSource.mapBeer(response)
        } catch (e: Exception) {
            throw e
        }
    }

    fun getSettingsInfo(): SettingsEntity {
        val localSettings = SettingsRaw(
            sharedPrefs.getFloat(NAME_SIZE, 0f),
            sharedPrefs.getString(NAME_COLOR, ""),
            sharedPrefs.getFloat(TAGLINE_SIZE, 0f),
            sharedPrefs.getString(TAGLINE_COLOR, ""),
            sharedPrefs.getBoolean(TAGLINE_VISIBLE, true),
            sharedPrefs.getInt(IMAGE_HEIGHT, 0),
            sharedPrefs.getInt(IMAGE_WIDTH, 0),
            sharedPrefs.getInt(IMAGE_ROUND_RADIUS, 0)
        )

        return settingsSource.mapSettings(localSettings)
    }

    fun setSettingsInfo(settingsEntity: SettingsEntity) {
        sharedPrefs[NAME_SIZE] = settingsEntity.nameSize
        sharedPrefs[NAME_COLOR] = settingsEntity.nameColor
        sharedPrefs[TAGLINE_SIZE] = settingsEntity.taglineSize
        sharedPrefs[TAGLINE_COLOR] = settingsEntity.taglineColor
        sharedPrefs[TAGLINE_VISIBLE] = settingsEntity.taglineVisible
        sharedPrefs[IMAGE_HEIGHT] = settingsEntity.imageHeight
        sharedPrefs[IMAGE_WIDTH] = settingsEntity.imageWidth
        sharedPrefs[IMAGE_ROUND_RADIUS] = settingsEntity.imageRoundRadius
    }
}