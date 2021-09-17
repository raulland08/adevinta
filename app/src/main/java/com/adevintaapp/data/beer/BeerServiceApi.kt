package com.adevintaapp.data.beer

import retrofit2.http.*

interface BeerServiceApi {

    @GET(".")
    suspend fun getRandomBeer(): List<BeerRaw>
}