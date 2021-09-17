package com.adevintaapp.framework.di

import com.adevintaapp.data.BeerRepository
import com.adevintaapp.data.BeerSource
import com.adevintaapp.data.SettingsSource
import com.adevintaapp.data.beer.BeerServiceApi
import com.adevintaapp.domain.usecase.GetRandomBeerUseCase
import com.adevintaapp.domain.usecase.GetSettingsInfoUseCase
import com.adevintaapp.domain.usecase.SaveSettingsInfoUseCase
import com.adevintaapp.ui.BeerVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val beerModule = module(override = true) {
    viewModel { BeerVM(get(), get(), get()) }

    single { GetRandomBeerUseCase(get()) }
    single { GetSettingsInfoUseCase(get()) }
    single { SaveSettingsInfoUseCase(get()) }

    single { BeerRepository(get(), get(), get(), get()) }

    single { BeerSource() }
    single { SettingsSource() }

    single { get<Retrofit>().create(BeerServiceApi::class.java) }

}

