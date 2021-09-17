package com.adevintaapp

import com.adevintaapp.data.BeerSource
import com.adevintaapp.data.beer.BeerRaw
import com.adevintaapp.domain.entity.BeerEntity
import io.mockk.coEvery
import io.mockk.coVerify
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BeerSourceTest {

    private lateinit var beerSource: BeerSource

    private lateinit var beerRaw: List<BeerRaw>
    private lateinit var beerEntity: BeerEntity

    @Before
    fun setUp(){
        beerSource = BeerSource()

        //Given
        beerRaw = listOf(BeerRaw(id = 1, name = "name", tagline = "tagline", image_url = "imageURL"))
        beerEntity = BeerEntity(1, "name", "tagline", "imageURL")
    }

    @Test
    fun `mapBeer method must return all info when tagline and imageUrl are not null`() {
        //When
        beerSource.mapBeer(beerRaw)

        //then
        assertTrue(beerRaw[0].id == beerEntity.id)
        assertTrue(beerRaw[0].name == beerEntity.name)
        assertTrue(beerRaw[0].tagline == beerEntity.tagline)
        assertTrue(beerRaw[0].image_url == beerEntity.imageUrl)
    }

    @Test
    fun `mapBeer method must return info even when tagline and imageUrl are null`() {
        //Given
        beerRaw[0].let {
            it.tagline = null
            it.image_url = null
        }

        //Expected
        beerEntity.let {
            it.tagline = null
            it.imageUrl = null
        }

        //When
        beerSource.mapBeer(beerRaw)

        //then
        assertTrue(beerRaw[0].id == beerEntity.id)
        assertTrue(beerRaw[0].name == beerEntity.name)
        assertTrue(beerRaw[0].tagline == beerEntity.tagline)
        assertTrue(beerRaw[0].image_url == beerEntity.imageUrl)
    }
}