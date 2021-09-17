package com.adevintaapp.domain.usecase

import com.adevintaapp.data.BeerRepository
import com.adevintaapp.domain.entity.SettingsEntity

class GetSettingsInfoUseCase(
    private val beerRepository: BeerRepository)
{
    operator fun invoke() : SettingsEntity = beerRepository.getSettingsInfo()
}