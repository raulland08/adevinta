package com.adevintaapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adevintaapp.domain.entity.BeerEntity
import com.adevintaapp.domain.entity.SettingsEntity
import com.adevintaapp.domain.usecase.GetRandomBeerUseCase
import com.adevintaapp.domain.usecase.GetSettingsInfoUseCase
import com.adevintaapp.domain.usecase.SaveSettingsInfoUseCase
import com.adevintaapp.ui.custom.Info
import kotlinx.coroutines.launch

class BeerVM(
    private val getRandomBeer: GetRandomBeerUseCase,
    private val getSettingsInfo: GetSettingsInfoUseCase,
    private val saveSettingsInfo: SaveSettingsInfoUseCase
): ViewModel() {

    private val _beer: MutableLiveData<BeerEntity> = MutableLiveData()
    val beer: LiveData<BeerEntity> get() = _beer

    private val _settings: MutableLiveData<SettingsEntity> = MutableLiveData()
    val settings: LiveData<SettingsEntity> get() = _settings

    private val _isErrorThrown = MutableLiveData(false)
    val isErrorThrown: LiveData<Boolean> get() = _isErrorThrown

    fun initLoading() {
        getBeers()
        getSettings()
    }

    private fun getBeers() {
        viewModelScope.launch {
            try {
                _beer.postValue(getRandomBeer())
            } catch (e: Exception) {
                _isErrorThrown.postValue(true)
            }
        }
    }

    private fun getSettings() {
        _settings.value = getSettingsInfo()
    }

    fun appStopped(info: Info) {
        saveSettingsInfo(
            SettingsEntity(
                info.nameSize,
                info.nameColor,
                info.taglineSize,
                info.taglineColor,
                info.taglineVisible,
                info.imageHeight,
                info.imageWidth,
                info.imageRoundRadius
            )
        )
    }
}