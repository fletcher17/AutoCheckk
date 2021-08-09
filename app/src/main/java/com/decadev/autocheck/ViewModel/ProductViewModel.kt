package com.decadev.autocheck.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decadev.autocheck.Model.Detail.CarDetail
import com.decadev.autocheck.Repository.MainRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel : ViewModel() {

    var mainRepository: MainRepository

    private var _carDetails = MutableLiveData<CarDetail>()
    val carDetails: LiveData<CarDetail> get() = _carDetails

    init {
        mainRepository = MainRepository()
    }


    fun getCarDetails(carId: String) {
        viewModelScope.launch(IO) {
            val getDetailsOfCar = mainRepository.getCarDetailsFromApi(carId)
            withContext(Main) {
                try {
                    getDetailsOfCar.let {
                        _carDetails.value = getDetailsOfCar
                    }
                } catch (e: Throwable) {
                    _carDetails.value = null
                }
            }
        }
    }


}