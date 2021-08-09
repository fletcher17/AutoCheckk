package com.decadev.autocheck.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decadev.autocheck.Model.Cars.CarsClass
import com.decadev.autocheck.Model.logo.LogoClass
import com.decadev.autocheck.Repository.MainRepository
import com.decadev.autocheck.Utils.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExploreViewModel : ViewModel() {

    enum class LogoResponseState { LOADING, SUCCESS, FAILURE }

    var mainRepository: MainRepository

    private var _allLogos = MutableLiveData<LogoClass>()
    val allLogos: LiveData<LogoClass> get() = _allLogos

    private var _allCars = MutableLiveData<CarsClass>()
    val allCars: LiveData<CarsClass> get() = _allCars

    private val _status = MutableLiveData<LogoClass>()
    val status: LiveData<LogoClass> get() = _status

    init {
        mainRepository = MainRepository()
        getAllLogoFromRepository()
        getAllCarsFromRepository()
    }


    /** Call is made to the repository on the background thread to fetch logo images from the Api,
     * and renders on the main thread
     * */
    fun getAllLogoFromRepository() {
        viewModelScope.launch(IO) {
            val getCarLogos = mainRepository.getAllLogoFromApi()
            withContext(Main) {
                try {
                    getCarLogos.let {
                        _allLogos.value = getCarLogos
                    }
                } catch (e: Throwable) {
                    _allLogos.value = null
                }
            }
        }
    }

    /** A call is made on the background thread to the mainrepository to fetch all cars from the api*/
    fun getAllCarsFromRepository() {
        viewModelScope.launch(IO) {
            val getCars = mainRepository.getAllCarsFromApi()
            withContext(Main) {
                try {
                    getCars.let {
                        _allCars.value = getCars
                        Constants.CarListData = getCars
                    }
                } catch (e: Throwable) {
                    _allCars.value = null
                }
            }
        }
    }

    /**This cancels the coroutine job in the view model scope once the UI isn't in view*/
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}