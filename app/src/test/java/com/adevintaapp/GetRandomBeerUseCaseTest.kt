package com.adevintaapp

import com.adevintaapp.data.BeerRepository
import com.adevintaapp.domain.entity.BeerEntity
import com.adevintaapp.domain.usecase.GetRandomBeerUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomBeerUseCaseTest {

    private lateinit var beerRepository: BeerRepository
    private lateinit var getRandomBeerUseCase: GetRandomBeerUseCase

    private lateinit var beerEntity: BeerEntity

    @Before
    fun setUp(){
        beerRepository = mockk()
        getRandomBeerUseCase = GetRandomBeerUseCase(beerRepository)

        beerEntity = BeerEntity(1, "name", "tagline", "imageURL")

        //Give
        coEvery { beerRepository.getRandomBeer() } returns beerEntity
    }

    @Test
    fun `GetRandomBeerUseCase must call repository method getRandomBeer`() = runBlocking{
        //When
        getRandomBeerUseCase.invoke()

        coVerify {
            beerRepository.getRandomBeer()
        }
    }
}