package com.adevintaapp.domain.usecase

import com.adevintaapp.data.BeerRepository
import com.adevintaapp.domain.entity.SettingsEntity

class SaveSettingsInfoUseCase(
    private val beerRepository: BeerRepository)
{
    operator fun invoke(settingsEntity: SettingsEntity) {
        beerRepository.setSettingsInfo(settingsEntity)
    }
}