package com.adevintaapp.domain.usecase

import com.adevintaapp.data.BeerRepository
import com.adevintaapp.domain.entity.BeerEntity

class GetRandomBeerUseCase(
    private val beerRepository: BeerRepository)
{
    suspend operator fun invoke() : BeerEntity = beerRepository.getRandomBeer()
}