package com.decadev.autocheck.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decadev.autocheck.Model.CarMediaLists.CarMediaX
import com.decadev.autocheck.Repository.MainRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MediaViewModel : ViewModel() {

    var mainRepository: MainRepository

    private var _carMediaDetails = MutableLiveData<List<CarMediaX>>()
    val carMediaDetails: LiveData<List<CarMediaX>> get() = _carMediaDetails

    init {
        mainRepository = MainRepository()
    }

    /**getCarMediaApi is being called in the Main repository with a card id passedas an argument */
    fun getCarMedia(carId: String) {
        viewModelScope.launch(IO) {
            var getCarMediaDetails = mainRepository.getCarMediaFromApi(carId)
            withContext(Main) {
                try {
                    getCarMediaDetails.let {
                        _carMediaDetails.value = getCarMediaDetails.carMediaList
                    }
                } catch (e: Throwable) {
                    _carMediaDetails.value = null
                }
            }
        }
    }
}