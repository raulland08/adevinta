package com.adevintaapp.data

import com.adevintaapp.data.beer.BeerRaw
import com.adevintaapp.domain.entity.BeerEntity

class BeerSource {

    fun mapBeer(beerRaw: List<BeerRaw>): BeerEntity {
        return BeerEntity(
            id = beerRaw[0].id,
            name = beerRaw[0].name,
            tagline = beerRaw[0].tagline,
            imageUrl = beerRaw[0].image_url
        )
    }
}